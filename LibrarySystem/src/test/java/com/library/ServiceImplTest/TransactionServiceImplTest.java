package com.library.ServiceImplTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.library.api.LibrarySystemApplicationTests;
import com.library.api.model.BookBean;
import com.library.api.service.ITransactionService;

import com.library.api.util.LibraryValidation;

public class TransactionServiceImplTest extends LibrarySystemApplicationTests {
	
	@Autowired
	private LibraryValidation transTest;
	
	@Autowired
	private ITransactionService transActionService;
	
	
	@Test
	public void testCheckOut()
	{
		int userId = 222;
		BookBean books = new BookBean();
		books.setAuthor("Newton");
		books.setBookcount("10");
		books.setBooktitle("gravitation");
		books.setBookId(1111);
		List<String> bookstatusList=transActionService.getUserBookStatus(userId, books);
		assertEquals("RN", "RS");
		int bookCount=transActionService.getUserBookCount(userId, books);
		assertEquals("3", "2");
	}
}
