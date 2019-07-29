package com.library.api.service;

import com.library.api.model.BookBean;

public interface IBookService {

	public BookBean  searchBooks(BookBean book);

	public int addBooks(BookBean book);

	public int updatebookStaus(BookBean book);
	public int removeBooks(int id);

	

}
