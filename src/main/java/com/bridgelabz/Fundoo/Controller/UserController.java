package com.bridgelabz.Fundoo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.Fundoo.Dto.UserDto;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Response.UserResponse;
import com.bridgelabz.Fundoo.ServiceImpl.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userimpl;
	
	@GetMapping("/getallusers")
	public ResponseEntity<UserResponse> getAllUsers()
	{
		List<UserEntity> users=userimpl.getall();
		return new ResponseEntity<UserResponse>(new UserResponse("users are",users,200),HttpStatus.OK);
	}
	@PostMapping("/registeruser")
	public ResponseEntity<UserResponse> registerUser(@RequestBody UserDto dto)
	{
		
		if(dto.getEmail()!=null && dto.getPassword()!=null && dto.getName()!=null)
		{
			if(userimpl.getUserByEmail(dto.getEmail())==true)
				return new ResponseEntity<UserResponse>(new UserResponse("email already exists", 406),HttpStatus.NOT_ACCEPTABLE);
			UserEntity user=userimpl.registerUser(dto);
			return new ResponseEntity<UserResponse>(new UserResponse("regitration sucess",user,201),HttpStatus.CREATED);
			
		}
		return new ResponseEntity<UserResponse>(new UserResponse("enter valid details",406),HttpStatus.NOT_ACCEPTABLE);
			
	}
	@GetMapping("/verifyemail/{token}")
	public ResponseEntity<UserResponse> verifyemail(@PathVariable("token") String token)
	{
		UserEntity user=userimpl.verify(token);
		if(user.getEmail()!=null)
		{
			return new ResponseEntity<UserResponse>(new UserResponse("email verified", user,200),HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(new UserResponse("user not found",404),HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable("userId") long userId)
	{
		if(userimpl.isIdPresent(userId)==true)
		{
			userimpl.deleteUser(userId);
			return new ResponseEntity<UserResponse>(new UserResponse("user deleted",200),HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(new UserResponse("no user found",404),HttpStatus.NOT_FOUND);
	}
	@GetMapping("/getuserbyid/{userId}")
	public ResponseEntity<UserResponse> getuserById(@PathVariable("userId") long userId)
	{
		UserEntity user=userimpl.getUserById(userId);
		if(user.getEmail()!=null)
		{
			return new ResponseEntity<UserResponse>(new UserResponse("user details are", user,200),HttpStatus.OK);
		}
		return new ResponseEntity<UserResponse>(new UserResponse("user not found",404),HttpStatus.NOT_FOUND);
	}
	
}
