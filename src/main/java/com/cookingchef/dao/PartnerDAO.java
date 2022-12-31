package com.cookingchef.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.cookingchef.model.Partner;

public interface PartnerDAO {

  /**
   * Registers a partner in the database
   * 
   * @param partner The partner object that you want to register.
   * @return The id of the newly created partner
   */
  public Optional<Integer> registerPartnerInDb(Partner partner) throws SQLException;

  /**
   * Update the partner in the database.
   * 
   * @param partner The partner object that you want to update in the database.
   */
  public void updatePartnerInDb(Partner partner) throws SQLException;

  /**
   * Removes a partner from the database.
   * 
   * @param partner The partner object to be removed from the database.
   */
  public void removePartnerFromDb(Partner partner) throws SQLException;

  /**
   * Get a partner by id
   * 
   * @param id The id of the partner you want to get.
   * @return The partner if it exists, otherwise, returns an empty option
   */
  public Optional<Partner> getPartnerById(int id) throws SQLException;

  /**
   * Returns a list of partners whose name matches the given name.
   * 
   * @param name The name of the partner you want to search for.
   * @return A list of partners with the name specified.
   */
  public List<Partner> getPartnersByName(String name) throws SQLException;


  /**
   * Returns the whole list of partners
   * 
   * @return Every partner in the database.
   */
  public default List<Partner> getPartners() throws SQLException {
    return getPartnersByName("");
  }

}
