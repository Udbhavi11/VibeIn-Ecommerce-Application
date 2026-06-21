package com.vibein.dao;

import java.util.List;

import com.vibein.model.User;

public interface UserDAO {

	boolean addUser(User user);

	User getUserById(int userId);

	User getUserByEmail(String email);

	List<User> getAllUsers();

	boolean updateUser(User user);

	boolean deleteUser(int userId);

	User validateUser(String email, String password);
}