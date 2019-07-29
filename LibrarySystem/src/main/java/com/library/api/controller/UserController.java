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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.model.BookBean;
import com.library.api.model.Users;
import com.library.api.service.IBookService;
import com.library.api.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/librarySystem")
@Api(value="libraryManagementSystem",description = "User Account For Library System")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	

	@ApiOperation(value ="Register Users")
	@PostMapping(value="/addUsers")
	public ResponseEntity<String> addUsers(@RequestBody Users user) {
		String response="User Created Successfully";
		userService.createUser(user);
		return new ResponseEntity<String>(response,HttpStatus.OK);
		}
	
	@ApiOperation(value ="Update User Account")
	@PutMapping(value="/update/{userid}",produces = "application/json")
	public ResponseEntity<String> updateUsers(@PathVariable("userid") int userid,@RequestBody Users user ) {
		String response;
		Users userObj=userService.getUser(userid);
		if(userObj==null)
		{
			return new ResponseEntity<>("This"+userid+"not found.", HttpStatus.NOT_FOUND);
		}
		userObj.setUserName(user.getUserName());
		userObj.setEmail(user.getEmail());
		userObj.setPassword(user.getPassword());
		int value=userService.updateUser(userObj);
		if(value==1)
		{
			 response="User Updated Successfully";
		}
		else
			 response="User Update Failed";
		return new ResponseEntity<String>(response,HttpStatus.OK);
		
	}
	
	@ApiOperation(value ="Memership Cancellation")	
	@DeleteMapping(value="/remove/{userid}",produces = "application/json")
	public ResponseEntity<String> removeUser(@PathVariable("userid") int userid) {
		String response;
		Users userObj=userService.getUser(userid);
		if(userObj==null)
		{
			return new ResponseEntity<>("Unable to cancel membership of user with id"+userid+"not found.", HttpStatus.NOT_FOUND);
		}
		int value=userService.removeUser(userid);
		if(value==1)
		{
			 response="Membership Cancelled Successfully";
			 return new ResponseEntity<String>(response,HttpStatus.NOT_FOUND);
		}
		else
			 response="Membership Cancellation Failed ";
		return new ResponseEntity<String>(response,HttpStatus.OK);
		
	}
	
	
}
