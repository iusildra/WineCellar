package com.cookingchef.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Popups {

  private Popups() {
  }

  public static void errorPopup(String msg) {
    Alert alert = new Alert(AlertType.ERROR, msg, ButtonType.OK);
    alert.show();
  }

  public static void confirmationPopup(String msg, Runnable callback) {
    Alert alert = new Alert(AlertType.CONFIRMATION, msg, ButtonType.YES, ButtonType.NO);
    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.YES) {
        callback.run();
      }
    });
  }
}
