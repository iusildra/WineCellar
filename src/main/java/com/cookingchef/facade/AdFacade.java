package com.cookingchef.facade;

import com.cookingchef.dao.AdDAO;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.Ad;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class AdFacade {
    private static AtomicReference<AdFacade> instance = new AtomicReference<>();
    private final AdDAO adDAO;

    protected AdFacade() {
        var factory = new PostgresFactory();
        this.adDAO = factory.getAdDAO();
    }

    public static AdFacade getAdFacade() {
        instance.compareAndSet(null, new AdFacade());
        return instance.get();
    }

    public Optional<Ad> addAd(Ad advert) throws SQLException {
        var newId = adDAO.createAdInDb(advert);

        if (newId.isPresent())
            return adDAO.getAdById(newId.get());

        return Optional.empty();
    }

    public void deleteAd(Ad ad) throws SQLException {
        adDAO.removeAdFromDb(ad);
    }

    public void updateAd(Ad ad) throws SQLException {
        adDAO.updateAdInDb(ad);
    }

    public Optional<Ad> getAdById(int id) throws SQLException {
        return adDAO.getAdById(id);
    }

    public ArrayList<Ad> getAllAds() throws SQLException {
        return adDAO.getAds();
    }
}