package com.library.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.model.BookBean;
import com.library.api.model.Users;
import com.library.api.service.IBookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/librarySystem")
@Api(value="BooksManagementSystem",description = "Books Management in a library Management System")
public class BookController {
	
	@Autowired
	private IBookService bookService;
	
	@ApiOperation(value ="Search Books with Title or Author")	
	@GetMapping(value="/Search/{book}",produces = "application/json")
	public ResponseEntity<String> searchBooks(@PathVariable BookBean book) {
		String response;
		
		BookBean bookObj=bookService.searchBooks(book); 
		if(bookObj!=null)
		{
			 response="Please find the book below";
			 return new ResponseEntity<String>(response,HttpStatus.NOT_FOUND);
		}
		else
			 response="Book Not Found ";
		return new ResponseEntity<String>(response,HttpStatus.OK);
		
	}
	
	
	@ApiOperation(value ="Add New Books")
	@PostMapping(value="/addBooks")
	public ResponseEntity<String> addUsers(@RequestBody BookBean book) {
		String response="Books Added Successfully";
		bookService.addBooks(book);
		return new ResponseEntity<String>(response,HttpStatus.OK);
		}
	
	@ApiOperation(value ="Update Book Details in Library")
	@PutMapping(value="/updateBooks/{bookid}",produces = "application/json")
	public ResponseEntity<String> updateBooks(@PathVariable("bookid") int bookid,@RequestBody BookBean book ) {
		String response;
		BookBean bookObj=bookService.searchBooks(book);
		if(bookObj==null)
		{
			return new ResponseEntity<>("Book Not Found.", HttpStatus.NOT_FOUND);
		}
		bookObj.setAuthor(book.getAuthor());
		bookObj.setBooktitle(book.getAuthor());
		bookObj.setBookcount(book.getBookcount());
		int value=bookService.updatebookStaus(bookObj);
		if(value==1)
		{
			 response="Book Details Updated Successfully";
		}
		else
			 response="Book Details Update Failed";
		return new ResponseEntity<String>(response,HttpStatus.OK);
		
	}
	
	@ApiOperation(value ="Remove Book")	
	@DeleteMapping(value="/removeBook/{book}",produces = "application/json")
	public ResponseEntity<String> removeBook(@PathVariable("bookid") int bookid,@RequestBody BookBean book) {
		String response;
		BookBean bookObj=bookService.searchBooks(book);
		if(bookObj==null)
		{
			return new ResponseEntity<>("Book Not Found.", HttpStatus.NOT_FOUND);
		}
		int value=bookService.removeBooks(bookid);
		if(value==1)
		{
			 response="Book Removed Successfully";
			 return new ResponseEntity<String>(response,HttpStatus.NOT_FOUND);
		}
		else
			 response="Not able to Remove Book" ;
		return new ResponseEntity<String>(response,HttpStatus.OK);
		
	}

}
