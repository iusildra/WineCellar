package com.cookingchef.partners;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Partner;

public class DAOTests {

  DAOTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
  }

  @Test
  void createPartner() throws SQLException {
    var partner = new Partner("abc", "abc", "abc");
    partner.createInDb();

    var newPartner = PostgresFactory.getPostgresFactory()
        .getPartnerDAO()
        .getPartnerById(partner.getId().get());

    assert newPartner.get().equals(partner);

    partner.removeFromDb();
  }

  @Test
  void updatePartner() throws SQLException {
    var partner = new Partner("abc", "abc", "abc");
    partner.createInDb();

    partner.setName("def");
    partner.setDescription("def");
    partner.setWebsite("def");

    partner.updateInDb();

    var fetchedPartner = PostgresFactory.getPostgresFactory()
        .getPartnerDAO()
        .getPartnerById(partner.getId().get())
        .get();

    partner.removeFromDb();

    assert fetchedPartner.equals(partner);
  }

  @Test
  void deletePartner() throws SQLException {
    var partner = new Partner("abc", "abc", "abc");
    partner.createInDb();

    partner.removeFromDb();

    var fetchedPartner = PostgresFactory.getPostgresFactory().getPartnerDAO().getPartnerById(partner.getId().get());

    assert !fetchedPartner.isPresent();
  }

  @Test
  void getAllPartners() throws SQLException {
    var partners = new ArrayList<Partner>();
    partners.add(new Partner("abc", "abc", "abc"));
    partners.add(new Partner("def", "def", "def"));
    partners.add(new Partner("ghi", "ghi", "ghi"));

    for (var partner : partners) {
      partner.createInDb();
    }

    var fetchedPartners = PostgresFactory.getPostgresFactory().getPartnerDAO().getPartners();

    for (var partner : partners) {
      partner.removeFromDb();
    }

    assert fetchedPartners.stream()
        .map(partner -> partner.getId().get())
        .collect(Collectors.toList())
        .containsAll(
            partners.stream()
                .map(partner -> partner.getId().get())
                .collect(Collectors.toList()));

    // assert fetchedPartners.containsAll(partners);
  }

  @Test
  void getPartnerById() throws SQLException {
    var partner = new Partner("abc", "abc", "abc");
    partner.createInDb();

    var fetchedPartner = PostgresFactory.getPostgresFactory()
        .getPartnerDAO()
        .getPartnerById(partner.getId().get())
        .get();

    partner.removeFromDb();

    assert fetchedPartner.equals(partner);
  }
}
