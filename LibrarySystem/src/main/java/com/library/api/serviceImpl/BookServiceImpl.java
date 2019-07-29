package com.library.api.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.library.api.model.BookBean;
import com.library.api.model.BookMapper;

import com.library.api.service.IBookService;

@Transactional
@Repository

public class BookServiceImpl implements IBookService{

	@Autowired
	 private JdbcTemplate jdbcTemplate;
	

	@Override
	public BookBean searchBooks(BookBean book) {
		
		String sqlSelectQuery = "SELECT title,bookcount FROM libbook WHERE (title = ? or author=?)and bookcount != 0";
		
		return jdbcTemplate.queryForObject(sqlSelectQuery, new Object[] { book.getBooktitle(),book.getAuthor(),book.getBookcount() }, new BookMapper());
		
	}
		 
    @Override
    public int addBooks(BookBean book) {
				
				 return jdbcTemplate.update(
			                "insert into libbook (bookid,title,author,libcount) values(?,?,?,?)",
			                book.getBookId(),book.getBooktitle(),book.getAuthor(),book.getBookcount() );
			}
		 
		 
	

   

	@Override
	public int updatebookStaus(BookBean book) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(
                "update libbook set bookcount=? where bookid=?",
                book.getBookcount(),book.getBookId());
	}

	@Override
	public int removeBooks(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(
                "delete from libbook where bookid = ?",
                id);
		
		
	}

	
	

	

	
	
	}

	
	
