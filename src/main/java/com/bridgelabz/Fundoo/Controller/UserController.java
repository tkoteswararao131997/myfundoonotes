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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.Fundoo.Dto.LoginDto;
import com.bridgelabz.Fundoo.Dto.UpdatePwdDto;
import com.bridgelabz.Fundoo.Dto.UserDto;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Exception.UserExceptions;
import com.bridgelabz.Fundoo.Response.UserResponse;
import com.bridgelabz.Fundoo.ServiceImpl.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userimpl;
	
	/**
	 * Register User : used to register the user
	 * @param dto
	 * @return register response
	 */
	@PostMapping("/registeruser")
	public ResponseEntity<UserResponse> registerUser(@RequestBody UserDto dto)
	{
			UserEntity user=userimpl.registerUser(dto);
			return new ResponseEntity<UserResponse>(new UserResponse("regitration sucess",user,201),HttpStatus.CREATED);		
	}
	/**
	 * Login User:used to login the user
	 * @param email,password
	 * @return login response
	 */
	
	@PostMapping("/loginuser")
	public ResponseEntity<UserResponse> loginUser(@RequestBody LoginDto dto)
	{
		UserEntity user=userimpl.loginUser(dto);
		return new ResponseEntity<UserResponse>(new UserResponse("login success","welcome "+user.getName(),200),HttpStatus.OK);
	}
	/**
	 * Get All Users: used to display all the users in the table
	 * @return list of users
	 */
	@GetMapping("/getallusers")
	public ResponseEntity<UserResponse> getAllUsers()
	{
		List<UserEntity> users=userimpl.getall();
		return new ResponseEntity<UserResponse>(new UserResponse("users are",users,200),HttpStatus.OK);
	}
	/**
	 * Verify Eamil : used to verify the email whether sent link is correct or not
	 * @param token
	 * @return verification response
	 */
	@GetMapping("/verifyemail/{token}")
	public ResponseEntity<UserResponse> verifyemail(@PathVariable("token") String token)
	{
		return new ResponseEntity<UserResponse>(new UserResponse("email verified",userimpl.verify(token),201),HttpStatus.ACCEPTED);
	}
	/**
	 * Delete User: used to delete the present user
	 * @param userId
	 * @return response of deleted or not
	 */
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable("userId") long userId)
	{
			userimpl.deleteUser(userId);
			return new ResponseEntity<UserResponse>(new UserResponse("user deleted",null,200),HttpStatus.OK);
	}
	/**
	 * Get User By id : get the user based upon user id in the table
	 * @param userId
	 * @return user
	 */
	@GetMapping("/getuserbyid/{userId}")
	public ResponseEntity<UserResponse> getuserById(@PathVariable("userId") long userId)
	{
		return new ResponseEntity<UserResponse>(new UserResponse("welcome",userimpl.getUserById(userId),200),HttpStatus.OK);
	}
	
	@PutMapping("/updatepassword")
	public ResponseEntity<UserResponse> updatePassword(@RequestBody UpdatePwdDto pwddto)
	{
		return new ResponseEntity<UserResponse>(new UserResponse("password updated successfully", userimpl.updatepwd(pwddto),200),HttpStatus.OK);
	}
}
