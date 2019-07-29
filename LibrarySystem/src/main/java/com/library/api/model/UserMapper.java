package com.library.api.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<Users> {



	@Override
	public Users mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Users user = new Users();
		user.setUserId(resultSet.getInt("userId"));
		user.setUserName(resultSet.getString("name"));
		user.setEmail(resultSet.getString("email"));
		user.setPassword(resultSet.getString("password"));
		return user;
	
	}
}
