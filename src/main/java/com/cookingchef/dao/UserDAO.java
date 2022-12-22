package com.cookingchef.dao;

import com.cookingchef.model.User;

public interface UserDAO {

	public abstract User getUserByEmailPwd(String email, String password);
}
