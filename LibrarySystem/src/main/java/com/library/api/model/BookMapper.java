package com.library.api.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookMapper implements RowMapper<BookBean> {
	
	@Override
	public BookBean mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		BookBean book = new BookBean();
		book.setBookId(resultSet.getInt("bookid"));
		book.setBooktitle(resultSet.getString("title"));
		book.setAuthor(resultSet.getString("author"));
		book.setBookcount(resultSet.getString("bookcount"));
		
		return book;
	
	}

}

