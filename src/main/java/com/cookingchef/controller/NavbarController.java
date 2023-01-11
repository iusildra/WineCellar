package com.cookingchef.controller;

import com.cookingchef.view.Main;
import com.cookingchef.view.Page;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

public class NavbarController implements Initializable {
	@FXML
	private MenuButton menuItems = new MenuButton();

	public void onClickButtonLogout() throws IOException {
		Main.setUser(null);
		Main.redirect("login");
	}

	public void onClickButtonProfile() throws IOException {
		Main.redirect("profile");
	}

	public void onClickButtonHome() throws IOException {
		Main.redirect("home");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Main.getUser() == null) {
			Notifications.create().title("Error").text("You must be logged in").showError();
			return;
		}

		if (Main.getUser().getIsAdmin()) {
			var list = adminItems();
			list.addAll(userItems());
			menuItems.getItems().setAll(list);
		} else {
			menuItems.getItems().addAll(userItems());
		}

		var logoutButton = new MenuItem("Logout");
		logoutButton.setOnAction(event -> {
			try {
				onClickButtonLogout();
			} catch (IOException e) {
				Notifications.create().title("Error").text("Couldn't logout").showError();
				e.printStackTrace();
			}
		});

		menuItems.getItems().add(logoutButton);

	}

	private void pageChange(Page page) {
		try {
			Main.redirect(page.value);
		} catch (IOException e) {
			Notifications.create().title("Error").text("Couldn't load page").showError();
			e.printStackTrace();
		}
	}

	public List<MenuItem> userItems() {
		var list = new ArrayList<MenuItem>();
		
		var partner = new MenuItem("Partner");
		partner.setOnAction(event -> pageChange(Page.PARTNER));
		var suggestions = new MenuItem("Suggestions");
		suggestions.setOnAction(event -> pageChange(Page.SUGGESTION));
		var recipeList = new MenuItem("Recipe List");
		recipeList.setOnAction(event -> pageChange(Page.RECIPE_LIST));
		
		list.add(partner);
		list.add(suggestions);
		list.add(recipeList);

		return list;
	}

	public List<MenuItem> adminItems() {
		var list = new ArrayList<MenuItem>();
		
		var ad = new MenuItem("Ad");
		ad.setOnAction(event -> pageChange(Page.AD));
		var category = new MenuItem("Category");
		category.setOnAction(event -> pageChange(Page.CATEGORY));
		var ingredient = new MenuItem("Ingredient");
		ingredient.setOnAction(event -> pageChange(Page.INGREDIENT));
		var userManagement = new MenuItem("User Management");
		userManagement.setOnAction(event -> pageChange(Page.USER_MANAGEMENT));
		var recipeAdmin = new MenuItem("Recipe Admin");
		recipeAdmin.setOnAction(event -> pageChange(Page.RECIPE_ADMIN));
		
		list.add(ad);
		list.add(category);
		list.add(ingredient);
		list.add(userManagement);

		return list;
	}

	public void onClickButtonCart() throws IOException {
		Main.redirect("cart");
	}
}
