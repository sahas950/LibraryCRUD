package com.library.crud.LibraryCrudM.dao;

import java.util.List;

import com.library.crud.LibraryCrudM.bean.User;

public interface UserDao {

	User getUserById(String userId);
	String createNewUser(User user);
	List<User> getAllUsers();
	
}
