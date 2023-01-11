package com.cookingchef.view;

public enum Page {
	AD("ad"),
	CATEGORY("category"),
	HOME("home"),
	INGREDIENT("ingredient"),
	LOGIN("login"),
	NAVBAR("navbar"),
	PARTNER("partner"),
	PROFILE("profile"),
	REGISTER("register"),
	RECIPE("recipe"),
	RECIPE_ADMIN("recipeAdmin"),
	RECIPE_LIST("recipe-list"),
	SUGGESTION("suggestion"),
	USER_MANAGEMENT("user-management");

	public final String value;

	Page(String value) {
		this.value = value;
	}
}
