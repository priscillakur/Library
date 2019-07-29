package com.library.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.library.api.model.Users;

public class LibraryRepoImpl  implements LibraryRepository {
	
	@Autowired
	 private JdbcTemplate jdbcTemplate;

	@Override
	public String save(Users users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Users users) {
		// TODO Auto-generated method stub
		return null;
	}

}
