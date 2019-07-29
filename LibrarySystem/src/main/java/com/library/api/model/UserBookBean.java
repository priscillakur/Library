package com.library.api.model;

import java.sql.Date;

public class UserBookBean {
	private int id;
	private int bookId;
	private int userId;
	private Date checkOutdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCheckOutdate() {
		return checkOutdate;
	}
	public void setCheckOutdate(Date checkOutdate) {
		this.checkOutdate = checkOutdate;
	}
	;
		
	
	

}
