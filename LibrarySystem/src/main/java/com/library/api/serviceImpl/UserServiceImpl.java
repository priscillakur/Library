package com.library.api.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Service;

import com.library.api.model.Users;
import com.library.api.model.UserMapper;

import com.library.api.service.IUserService;

@Service
public class UserServiceImpl implements IUserService  {


	@Autowired
	 private JdbcTemplate jdbcTemplate;
	

	@Override
	public int createUser(Users users) {
		
		 return jdbcTemplate.update(
	                "insert into libuser (userid,name,email,password) values(?,?,?,?)",
	                users.getUserId(),users.getUserName(),users.getEmail(),users.getPassword() );
	}
	
	@Override
	public int updateUser(Users users) {
		return jdbcTemplate.update(
                "update libuser set name = ? ,email=?,password =? where userid = ?",
                users.getUserName(),users.getEmail(),users.getPassword(), users.getUserId());
	}

	@Override
	public int removeUser(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(
                "delete from libuser where userid = ?",
                id);
		
	}

	@Override
	public Users getUser(int id) {
		// TODO Auto-generated method stub
		 String sqlSelectQuery = "SELECT * FROM libuser WHERE userid = ?";  
		 return jdbcTemplate.queryForObject(sqlSelectQuery, new Object[] { id }, new UserMapper());
		
	}

}

	


