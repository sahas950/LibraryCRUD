package com.library.crud.LibraryCrudM.service;

import java.util.List;

import com.library.crud.LibraryCrudM.bean.User;

public interface UserService {

	User getUserByUserId(String userId);
	String createNewUser(User user);
	List<User> getAllUsers();
	
}
