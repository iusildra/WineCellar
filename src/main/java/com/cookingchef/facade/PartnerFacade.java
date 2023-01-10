package com.cookingchef.facade;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import com.cookingchef.dao.PartnerDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Partner;

public class PartnerFacade {

  private static AtomicReference<PartnerFacade> instance = new AtomicReference<>();
  private final PartnerDAO partnerDAO;

  private PartnerFacade() {
    var factory = new PostgresFactory();
    this.partnerDAO = factory.getPartnerDAO();
  }

  public static PartnerFacade getPartnerFacade() {
    instance.compareAndSet(null, new PartnerFacade());
    return instance.get();
  }

  public Optional<Partner> createPartner(Partner partner) throws SQLException {
    var newId = partnerDAO.registerPartnerInDb(partner);

    if (newId.isPresent())
      return Optional.of(partner);

    return Optional.empty();
  }

  public void updatePartner(Partner partner) throws SQLException {
    partnerDAO.updatePartnerInDb(partner);
  }

  public void deletePartner(Partner partner) throws SQLException {
    partnerDAO.removePartnerFromDb(partner);
  }

  public Optional<Partner> getPartnerById(int id) throws SQLException {
    return partnerDAO.getPartnerById(id);
  }

  public List<Partner> getPartnerByName(String name) throws SQLException {
    return partnerDAO.getPartnersByName(name);
  }

  public List<Partner> getAllPartners() throws SQLException {
    return partnerDAO.getPartners();
  }
}
