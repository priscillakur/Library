package com.library.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.library.api.model.BookBean;
import com.library.api.service.IBookService;
import com.library.api.service.ITransactionService;
import com.library.api.util.LibraryValidation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/librarySystem")
@Api(value = "libraryManagementSystem", description = "Creating a library Management System")
public class LibraryController {

	@Autowired
	private IBookService bookService;

	
	
	
	LibraryValidation libObject=new LibraryValidation();
	  

	@ApiOperation(value = "Reserve Books For a User")
	@GetMapping(value = "/reserveBooks/{books}")
	public ResponseEntity<String> reserveBooks(@PathVariable("userid") int userid, @PathVariable BookBean books) {
		BookBean bookObj = bookService.searchBooks(books);
		if (bookObj == null) {
			return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
		}
		
	
		bookObj.setBookId(books.getBookId());
		
		String response = libObject.ValidateReservation(userid, bookObj);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}
	
	
	@ApiOperation(value = "Checkout Books For a User")
	@GetMapping(value = "/checkoutBooks/{books}")
	public ResponseEntity<String> checkoutBooks(@PathVariable("userid") int userid, @PathVariable BookBean books) {
		
		BookBean bookObj = bookService.searchBooks(books);
		if (bookObj == null) {
			return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
		}
	
	
		
		
		String response = libObject.ValidateCheckOut(userid, bookObj);
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}
	
	@ApiOperation(value = "Book Renewal")
	@GetMapping(value = "/renewalBooks/{books}")
	public ResponseEntity<String> renewalBooks(@PathVariable("userid") int userid, @PathVariable BookBean books) {
		
		BookBean bookObj = bookService.searchBooks(books);
		if (bookObj == null) {
			return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
		}
		bookObj.setBookId(books.getBookId());
	
		
		String response = libObject.ValidateRenewal(userid, bookObj);
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}
	
	
	@ApiOperation(value = "Book Return")
	@GetMapping(value = "/returnBooks/{books}")
	public ResponseEntity<String> returnBooks(@PathVariable("userid") int userid, @PathVariable BookBean books) {

		
		
		String response = libObject.ValidateReturn(userid,books);
		
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}
	
}
