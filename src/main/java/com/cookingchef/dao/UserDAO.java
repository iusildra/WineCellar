package com.cookingchef.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.cookingchef.model.User;

public interface UserDAO {


	/**
	 * Registers a user in the database and returns the user's id if the registration was
	 * successful, otherwise it returns an empty Optional.
	 *
	 * @param user The user object that you want to register in the database.
	 * @return An optional integer.
	 */
	public Optional<User> registerUserInDb(User user) throws SQLException;

	/**
	 * Updates a user in the database.
	 * 
	 * @param user The user object that you want to update in the database.
	 */
	public void updateUserInDb(User user) throws SQLException;

	/**
	 * Removes a user from the database.
	 * 
	 * @param user The user object to be removed from the database.
	 */
	public void removeUserFromDb(User user) throws SQLException;

	/**
	 * Fetch this list of users from the database
	 * 
	 * @return A list of users from the database.
	 */
	public List<User> getUsersFromDb() throws SQLException;

	/**
	 * Fetch a user from the database by id.
	 * 
	 * @param id The id of the user you want to get.
	 * @return An Optional object that contains a User object.
	 */
	public Optional<User> getUserById(int id) throws SQLException;

	/**
	 * Fetch a user from the database by email and password.
	 * 
	 * @param email The email of the user
	 * @param password The password of the user.
	 * @return Optional<User>
	 */
	public Optional<User> getUserByEmailPwd(String email, String password) throws SQLException;


}
