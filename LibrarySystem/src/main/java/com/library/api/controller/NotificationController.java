package com.library.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.model.BookBean;
import com.library.api.model.TransactionBean;
import com.library.api.model.Users;
import com.library.api.service.ITransactionService;

import io.swagger.annotations.ApiOperation;

@RestController
public class NotificationController {

	@Autowired
	private ITransactionService transActionService;
	@Autowired
    private JavaMailSender javaMailSender;
	

	@ApiOperation(value ="Overdue Notification Mail if more than 14 days for a  book to checkout/reserve/renew")
	@RequestMapping(value="/notificationMail")
	@ResponseBody()
public ResponseEntity<String> sendNotificationMail() {
		List<Users> userList=transActionService.sendnotificationMail();
		String response="No Data Found";
		if(userList.size()!=0 &&!userList.isEmpty())
		{
			
			for(Users users : userList){
				response=sendNotification(users.getEmail());
			}
			
		}
		return new ResponseEntity<String>(response,HttpStatus.OK);    
}
	
	public String sendNotification(String email)
	{
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Overdue Notification Mail");
        msg.setText("Hello,Your due date is over; Please renew/return book");

        javaMailSender.send(msg);
        return "Mail Sent Successfully";
	}
}
