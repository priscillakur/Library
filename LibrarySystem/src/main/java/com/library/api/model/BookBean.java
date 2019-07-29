package com.library.api.model;

public class BookBean {

	private int bookId;
	private String booktitle;
	private String author;

	//Available-A,reserve-R,Checkout-C,Renewl-RN,Return-RT	
	private String bookcount;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBooktitle() {
		return booktitle;
	}
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
	public String getBookcount() {
		return bookcount;
	}
	public void setBookcount(String bookcount) {
		this.bookcount = bookcount;
	}
	
	
	

}
