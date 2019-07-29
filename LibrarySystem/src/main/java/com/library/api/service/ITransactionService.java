package com.library.api.service;

import java.util.List;

import com.library.api.model.BookBean;
import com.library.api.model.TransactionBean;
import com.library.api.model.Users;


public interface ITransactionService {

	public int updateReservation(int userid,BookBean books);
	public List<Users> sendnotificationMail();
	public List<String> getUserBookStatus(int userId,BookBean books);
	public int getUserBookCount(int userId,BookBean books);
	public int updateCheckout(int userId,BookBean books);
	public int updateCheckoutStatus(int userId,BookBean books,String bookStatus);
	public int returnBooks(int userid,BookBean books);
	public int renewalBook(int userid,BookBean books);
}
