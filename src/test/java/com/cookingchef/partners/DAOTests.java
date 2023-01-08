package com.cookingchef.partners;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.cookingchef.dao.PartnerDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Partner;

public class DAOTests {

  final PartnerDAO partnerDAO;

  DAOTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
    var factory = new PostgresFactory();
    this.partnerDAO = factory.getPartnerDAO();
  }

  @Test
  void createPartner() throws SQLException {
    var partner = new Partner("abc", "abc", "abc");
    partnerDAO.registerPartnerInDb(partner);

    var newPartner = partnerDAO.getPartnerById(partner.getId().get());
    assert newPartner.get().equals(partner);

    partnerDAO.removePartnerFromDb(partner);
  }

  @Test
  void updatePartner() throws SQLException {
    var partner = new Partner("abc", "abc", "abc");
    partnerDAO.registerPartnerInDb(partner);

    partner.setName("def");
    partner.setDescription("def");
    partner.setWebsite("def");

    partnerDAO.updatePartnerInDb(partner);

    var fetchedPartner = partnerDAO.getPartnerById(partner.getId().get()).get();

    partnerDAO.removePartnerFromDb(partner);

    assert fetchedPartner.equals(partner);
  }

  @Test
  void deletePartner() throws SQLException {
    var partner = new Partner("abc", "abc", "abc");
    partnerDAO.registerPartnerInDb(partner);

    partnerDAO.removePartnerFromDb(partner);

    var fetchedPartner = partnerDAO.getPartnerById(partner.getId().get());

    assert !fetchedPartner.isPresent();
  }

  @Test
  void getAllPartners() throws SQLException {
    var partners = new ArrayList<Partner>();
    partners.add(new Partner("abc", "abc", "abc"));
    partners.add(new Partner("def", "def", "def"));
    partners.add(new Partner("ghi", "ghi", "ghi"));

    for (var partner : partners) {
      partnerDAO.registerPartnerInDb(partner);
    }

    var fetchedPartners = partnerDAO.getPartners();

    for (var partner : partners) {
      partnerDAO.removePartnerFromDb(partner);
    }

    assert fetchedPartners.stream()
        .map(partner -> partner.getId())
        .collect(Collectors.toList())
        .containsAll(
            partners.stream()
                .map(partner -> partner.getId())
                .collect(Collectors.toList()));

    // assert fetchedPartners.containsAll(partners);
  }

  @Test
  void getPartnerById() throws SQLException {
    var partner = new Partner("abc", "abc", "abc");
    partnerDAO.registerPartnerInDb(partner);

    var fetchedPartner = partnerDAO.getPartnerById(partner.getId().get()).get();

    partnerDAO.removePartnerFromDb(partner);

    System.out.println(partner.equals(fetchedPartner));
  }
}
