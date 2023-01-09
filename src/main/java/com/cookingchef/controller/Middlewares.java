package com.cookingchef.controller;

import java.util.Optional;

import com.cookingchef.model.User;

public class Middlewares {
  private Middlewares() {
  }

  public static boolean mustBeLoggedIn(Optional<Integer> userId) {
    if (userId.isPresent())
      return true;

    Popups.errorPopup("You must be logged in");
    return false;
  }

  public static boolean mustBeAdmin(User user) {
    if (user.getIsAdmin())
      return true;

    Popups.errorPopup("You must be an admin");
    return false;
  }
  
  public static boolean requiresValues(Optional<?> opt, String msgIfEmpty) {
    if (opt.isPresent())
      return true;

    Popups.errorPopup(msgIfEmpty);
    return false;
  }
}
