package com.cookingchef.controller;

import java.util.Optional;

import org.controlsfx.control.Notifications;

import com.cookingchef.model.User;

public class Middlewares {

  private static final String ERROR_TITLE = "Error";

  private Middlewares() {
  }

  public static boolean mustBeLoggedIn(Optional<Integer> userId) {
    if (userId.isPresent())
      return true;

    Notifications.create().title(ERROR_TITLE).text("You must be logged in").showError();
    return false;
  }

  public static boolean mustBeAdmin(User user) {
    if (user.getIsAdmin())
      return true;

    Notifications.create().title(ERROR_TITLE).text("You must be an admin").showError();
    return false;
  }
  
  public static boolean requiresValues(Optional<?> opt, String msgIfEmpty) {
    if (opt.isPresent())
      return true;

    Notifications.create().title(ERROR_TITLE).text(msgIfEmpty).showError();
    return false;
  }
}
