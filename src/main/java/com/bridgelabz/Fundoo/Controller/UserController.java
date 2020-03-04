package com.bridgelabz.Fundoo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public List<UserEntity> getAllUsers()
	{
		return userimpl.getall();
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
	
	
}
