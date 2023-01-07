package com.cookingchef.dao;

import com.cookingchef.model.Ad;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AdDAO {
    /**
     * Create an advertising in the database
     *
     * @param ad The ad object that you want to register.
     * @return The id of the newly created ad
     */
    public Optional<Integer> createAdInDb(Ad ad) throws SQLException;

    /**
     * Update the ad in the database.
     *
     * @param ad The ad object that you want to update in the database.
     */
    public void updateAdInDb(Ad ad) throws SQLException;

    /**
     * Removes an ad from the database.
     *
     * @param ad The ad object to be removed from the database.
     */
    public void removeAdFromDb(Ad ad) throws SQLException;

    /**
     * Get an ad by id
     *
     * @param id The id of the ad you want to get.
     * @return The ad if it exists, otherwise, returns an empty option
     */
    public Optional<Ad> getAdById(int id) throws SQLException;

    /**
     * Returns a list of ads whose name matches the given name.
     *
     * @param name The name of the ad you want to search for.
     * @return A list of ads with the name specified.
     */
    public List<Ad> getAdsByName(String name) throws SQLException;


    /**
     * Returns the whole list of ads
     *
     * @return Every ad in the database.
     */
    public default List<Ad> getAds() throws SQLException {
        return getAdsByName("");
    }
}
