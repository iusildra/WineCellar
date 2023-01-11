package com.cookingchef.controller;

import com.cookingchef.view.Main;

import java.io.IOException;

public class HomeController {

	public void onClickButtonLogout() throws IOException {
		Main.setUser(null);
		Main.redirect("login");
	}

	public void onClickButtonProfile() throws IOException {
		Main.redirect("profile");
	}


}
