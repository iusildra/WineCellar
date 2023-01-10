package com.cookingchef.ads;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.cookingchef.dao.AdDAO;
import com.cookingchef.dao.PartnerDAO;
import com.cookingchef.dao.Postgres.PostgresAdDAO;
import com.cookingchef.dao.Postgres.PostgresPartnerDAO;
import com.cookingchef.model.Ad;
import com.cookingchef.model.Partner;

class DAOTests {
  private AdDAO adDao;
  private PartnerDAO partnerDAO;

  public DAOTests() {
    this.adDao = PostgresAdDAO.getPostgresAdDAO();
    this.partnerDAO = PostgresPartnerDAO.getPostgresPartnerDAO();
  }

  @Test
  void createAdInDb() throws SQLException {
    var partner = new Partner("p", "p", "p");
    partnerDAO.registerPartnerInDb(partner);

    var ad = new Ad("abcdefg", 10, partner.getId().get(), 0);
    adDao.createAdInDb(ad);

    var newAd = adDao.getAdById(ad.getId().get());

    assert newAd.get().equals(ad);

    partnerDAO.removePartnerFromDb(partner);
    adDao.removeAdFromDb(ad);
  }

  @Test
  void updateAdInDb() throws SQLException {
    var partner = new Partner("p", "p", "p");
    partnerDAO.registerPartnerInDb(partner);

    var ad = new Ad("abcdefg", 10, partner.getId().get(), 0);
    adDao.createAdInDb(ad);

    ad.setDescriptionPromotion("hijklmn");
    ad.setPrice(20);
    adDao.updateAdInDb(ad);

    var newAd = adDao.getAdById(ad.getId().get());

    assert newAd.get().equals(ad);

    partnerDAO.removePartnerFromDb(partner);
    adDao.removeAdFromDb(ad);
  }

  @Test
  void deleteAdInDb() throws SQLException {
    var partner = new Partner("p", "p", "p");
    partnerDAO.registerPartnerInDb(partner);

    var ad = new Ad("abcdefg", 10, partner.getId().get(), 0);
    adDao.createAdInDb(ad);

    adDao.removeAdFromDb(ad);

    var newAd = adDao.getAdById(ad.getId().get());

    assert newAd.isEmpty();

    partnerDAO.removePartnerFromDb(partner);
  }
}
