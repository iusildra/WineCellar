package com.cookingchef.Facade;

public class UserFacade {
	private static volatile UserFacade userFacade;

	private UserFacade() {
	}

	public static synchronized UserFacade createUserFacade() {
		if (userFacade == null) {
			userFacade = new UserFacade();
		}
		return userFacade;
	}
}
