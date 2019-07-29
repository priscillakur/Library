package com.library.api.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.library.api.model.BookBean;
import com.library.api.model.Users;
import com.library.api.service.ITransactionService;

public class LibraryValidation  
{
	
	
		@Autowired
		private ITransactionService transActionService;
		
	public String ValidateReservation(int userId, BookBean books) {
		
		List<String> bookstatusList=transActionService.getUserBookStatus(userId, books);
		if(bookstatusList.size()!=0 &&!bookstatusList.isEmpty())
		{
			
			for(String bookstatus : bookstatusList){
		
			if((bookstatus.equalsIgnoreCase("RS")))  //RS-RESERVED
			{
				return "Sorry,This user is alreday reserved for this Book ";
			}
			if((bookstatus.equalsIgnoreCase("RN"))||(bookstatus.equalsIgnoreCase("CO")))  //RN- RENEW,CO- CHECKOUT
			{
				return "Sorry,This user is alreday checked out this Book ";
			}
			int bookCount=transActionService.getUserBookCount(userId, books);
			if(bookCount>3)
			{
				return "Sorry,You have reached the maximum number of books per person ";
			}
		}
		}
			transActionService.updateReservation(userId, books);
		return "Book Reserved Successfully";
		
	}
	

public String ValidateCheckOut(int userId, BookBean books) {
		
		
		
		List<String> bookstatusList=transActionService.getUserBookStatus(userId, books);
		if(bookstatusList.size()!=0 &&!bookstatusList.isEmpty())
		{
			
			for(String bookstatus : bookstatusList){
			
			if((bookstatus.equalsIgnoreCase("RN"))||(bookstatus.equalsIgnoreCase("CO")))  //RN- RENEW,CO- CHECKOUT
			{
				return "Sorry,This user is alreday checked out this Book ";
			}
			if(bookstatus.equalsIgnoreCase("RS"))
			{
				int bookCount=transActionService.getUserBookCount(userId, books);
				if(bookCount>3)
				{
					return "Sorry,You have reached the maximum number of books per person ";
				}
				transActionService.updateCheckoutStatus(userId, books,bookstatus);
				return "Book Checked out Successfully";
			}
			
		}
		}
		transActionService.updateCheckout(userId, books);
		return "Book Checked out Successfully";
		
	}
	

public String ValidateRenewal(int userid, BookBean books) {
	
	List<String> bookstatusList=transActionService.getUserBookStatus(userid, books);
	if(bookstatusList.size()!=0 &&!bookstatusList.isEmpty())
	{
		
		for(String bookstatus : bookstatusList){
		
		if(bookstatus.equalsIgnoreCase("RN") ) //RN- RENEW,CO- CHECKOUT
		{
			return "Sorry,You have reached maximum count of renewal ";
		}
		if(bookstatus.equalsIgnoreCase("CO"))
		{
			transActionService.renewalBook(userid, books);
			return "Book Renewed Successfully";
		}
		
		}
	
}
	return "No Book Details Found";
}
	
	public String ValidateReturn(int userid, BookBean books) {
		
		List<String> bookstatusList=transActionService.getUserBookStatus(userid, books);
		if(bookstatusList.size()!=0 &&!bookstatusList.isEmpty())
		{
			
			for(String bookstatus : bookstatusList){
			
			if(bookstatus.equalsIgnoreCase("RN") ||bookstatus.equalsIgnoreCase("CO")) //RN- RENEW,CO- CHECKOUT
			{
				transActionService.returnBooks(userid, books);
				return "Book Returned Successfully";
			}
			
			
			}
	}
		return "No Book Details Found";
}
}
