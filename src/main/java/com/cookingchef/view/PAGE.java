package com.cookingchef.view;

public enum PAGE {
	LOGIN("login"),
	HOME("home"),
	PROFILE("profile"),
	REGISTER("register"),
	;

	private final String name;

	PAGE(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
