package com.cookingchef.cart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.cookingchef.dao.CartDAO;
import com.cookingchef.dao.UserDAO;
import com.cookingchef.dbutils.ConnectionManager;
import com.cookingchef.factory.PostgresFactory;
import com.cookingchef.model.CartEntry;

class DAOTests {

  private CartDAO cartDAO;
  private UserDAO userDAO;

  public DAOTests() {
    ConnectionManager.openConnectionPool("postgres", "postgres", "postgres", 5432);
    var factory = new PostgresFactory();
    this.cartDAO = factory.getCartDAO();
    this.userDAO = factory.getUserDAO();
  }

  @Test
  void addElementIntoCart() throws SQLException {
    var cartEntry = new CartEntry(0, 0, 10, 0);
    var user = userDAO.getUserById(0).get();
    cartDAO.addElementIntoCartInDb(cartEntry);

    var newEntries = cartDAO.getCartByUser(user.getId().get());
    cartDAO.removeCartFromDb(cartEntry);

    assertEquals(cartEntry, newEntries.get(0));
  }

  @Test
  void updateElementInCard() throws SQLException {
    var cartEntry = new CartEntry(0, 0, 10,0);
    var user = userDAO.getUserById(0).get();
    cartDAO.addElementIntoCartInDb(cartEntry);

    var newEntries = cartDAO.getCartByUser(user.getId().get());

    cartEntry.setQuantity(20);
    cartDAO.updateCartInDb(cartEntry);
    newEntries = cartDAO.getCartByUser(user.getId().get());
    cartDAO.removeCartFromDb(cartEntry);

    assertEquals(cartEntry, newEntries.get(0));
  }

  @Test
  void deleteCartEntry() throws SQLException {
    var cartEntry = new CartEntry(0, 0, 10, 0);
    var user = userDAO.getUserById(0).get();
    cartDAO.addElementIntoCartInDb(cartEntry);

    var newEntries = cartDAO.getCartByUser(user.getId().get());

    cartDAO.removeCartFromDb(cartEntry);
    newEntries = cartDAO.getCartByUser(user.getId().get());
    
    assertEquals(0, newEntries.size());
  }

  @Test
  void getCartByUser() throws SQLException {
    var entries = new ArrayList<CartEntry>();
    entries.add(new CartEntry(0, 0, 10, 0));
    entries.add(new CartEntry(1, 0, 20, 0));
    entries.add(new CartEntry(2, 0, 30, 0));

    var user = userDAO.getUserById(0).get();
    for (var entry : entries) {
      cartDAO.addElementIntoCartInDb(entry);
    }

    var cart = cartDAO.getCartByUser(user.getId().get());

    for (var entry : entries) {
      cartDAO.removeCartFromDb(entry);
    }

    assertEquals(3, cart.size());
  }

  @Test
  void getCartEntryById() throws SQLException {
    var cartEntry = new CartEntry(0, 0, 10, 0);
    cartDAO.addElementIntoCartInDb(cartEntry);

    var newCartEntry = cartDAO.getCartById(cartEntry.getIngredientId(), cartEntry.getUserId()).get();
    cartDAO.removeCartFromDb(cartEntry);

    assertEquals(cartEntry, newCartEntry);
  }
}
