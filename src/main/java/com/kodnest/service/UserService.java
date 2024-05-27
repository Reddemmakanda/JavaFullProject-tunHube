package com.kodnest.service;

import com.kodnest.entity.User;

public interface UserService {

	boolean emailExists(User user);

	void saveUser(User user);

	boolean validUser(String email, String password);

	String getRole(String email);

	User getUser(String email);

	void updateUser(User user);

	void resetPassword(String email, String password);

}
