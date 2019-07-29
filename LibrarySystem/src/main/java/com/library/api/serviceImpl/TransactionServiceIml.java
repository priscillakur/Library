package com.library.api.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.library.api.model.BookBean;
import com.library.api.model.TransactionBean;
import com.library.api.model.UserMapper;
import com.library.api.model.Users;
import com.library.api.service.ITransactionService;

@Component
public class TransactionServiceIml implements ITransactionService{

	@Autowired
	 private JdbcTemplate jdbcTemplate;
	@Override
	public int updateReservation(int userId, BookBean books) {
		
				 
				 return jdbcTemplate.update(
			                "insert into libtrx (userid,bookid,trx_date,trx_status) values(?,?,?,?);"+
			                		"update libbook set bookcount=(bookcount-1) where bookid=?;",
			                userId,books.getBookId(),LocalDate.now(),"RS",books.getBookId());
				 
				 
				 
			//	 "update libbook set bookcount=(bookcount-1) where bookid=?;",
	}

	@Override
	public List<Users> sendnotificationMail() {
		// TODO Auto-generated method stub
		
		
				String sqlSelectQuery = "select email from libuser where userid=(select userid from libtrx where trx_date<= (LocalDate.now()-14) and trx_status in ('RS','RN','CO'))";  
				List<Users> usersList= jdbcTemplate.query(sqlSelectQuery,new BeanPropertyRowMapper(Users.class));
		 return usersList;
	}

	@Override
	public  List<String> getUserBookStatus(int userId, BookBean books) {

		//return all status for bookid and userid
		String sqlSelectQuery = " select trx_status from libtrx where userid=? and bookid=? ";
		List<String> bookStatus =jdbcTemplate.query(sqlSelectQuery,new BeanPropertyRowMapper(TransactionBean.class));
		return bookStatus;
	}

	@Override
	public int getUserBookCount(int userId, BookBean books) {
		 return jdbcTemplate.update("select count(*) from libtrx where userid=? and trx_status IN ('RS','RN','CO')");
		
	}

	@Override
	public int updateCheckout(int userId, BookBean books) {
		// TODO Auto-generated method stub
		 return jdbcTemplate.update(
	                "insert into libtrx (userid,bookid,trx_date,trx_status) values(?,?,?,?);"+
	                		"update libbook set bookcount=(bookcount-1) where bookid=?;",
	                userId,books.getBookId(),LocalDate.now(),"CO",books.getBookId());
		 
	}

	@Override
	public int updateCheckoutStatus(int userId, BookBean books,String bookStatus) {
		// TODO Auto-generated method stub
		 return jdbcTemplate.update("update libtrx set trx_status=? where bookid=? and user_id=? and trx_status=?;",
	                "CO",books.getBookId(),userId,bookStatus);
		 
	}

	@Override
	public int returnBooks(int userId, BookBean books) {
		// count increase one and change staus to RN
		
		
		return jdbcTemplate.update(
                "update libtrx set,trx_date=?,trx_status=? values(?,?) where bookid=? and userid=?;"+
                		"update libbook set bookcount=(bookcount+1) where bookid=?;",
                LocalDate.now(),"RN",books.getBookId(),userId,books.getBookId());
	}

	@Override
	public int renewalBook(int userId, BookBean books) {
		//  date update only
		 return jdbcTemplate.update(	                
	                		"update libtrx set trx_date=? where bookid=? and userid=?;",
	                LocalDate.now(),books.getBookId(),userId);
		 
	}

	

}
