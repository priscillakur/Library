package com.library.api.service;

import com.library.api.model.Users;

public interface IUserService {

	public int createUser(Users users);
	public Users getUser(int id);
	public int updateUser(Users users);
	public int removeUser(int id);
	
	
}
