package com.bridgelabz.Fundoo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.Fundoo.Dto.ForgotPwdDto;
import com.bridgelabz.Fundoo.Dto.LoginDto;
import com.bridgelabz.Fundoo.Dto.UpdatePwdDto;
import com.bridgelabz.Fundoo.Dto.UserDto;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Response.Response;
import com.bridgelabz.Fundoo.Service.AmazonS3ClientService;
import com.bridgelabz.Fundoo.ServiceImpl.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userimpl;
	@Autowired
    private AmazonS3ClientService amazonS3ClientService;
	
	/**
	 * Register User : used to register the user
	 * @param dto
	 * @return register response
	 */
	@PostMapping("/registeruser")
	public ResponseEntity<Response> registerUser(@RequestBody UserDto dto)
	{
			UserEntity user=userimpl.registerUser(dto);
			return new ResponseEntity<Response>(new Response("regitration sucess",user,201),HttpStatus.CREATED);		
	}
	/**
	 * Login User:used to login the user
	 * @param email,password
	 * @return login response
	 */
	
	@PostMapping("/loginuser")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDto dto)
	{
		String token=userimpl.loginUser(dto);
		return new ResponseEntity<Response>(new Response("login success","token is:"+token,200),HttpStatus.OK);
	}
	/**
	 * Get All Users: used to display all the users in the table
	 * @return list of users
	 */
	@GetMapping("/getallusers")
	public ResponseEntity<Response> getAllUsers()
	{
		List<UserEntity> users=userimpl.getall();
		return new ResponseEntity<Response>(new Response("users are",users,200),HttpStatus.OK);
	}
	/**
	 * Verify Eamil : used to verify the email whether sent link is correct or not
	 * @param token
	 * @return verification response
	 */
	@GetMapping("/verifyemail/{token}")
	public ResponseEntity<Response> verifyemail(@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("email verified",userimpl.verify(token),201),HttpStatus.ACCEPTED);
	}
	/**
	 * Delete User: used to delete the present user
	 * @param userId
	 * @return response of deleted or not
	 */
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<Response> deleteUser(@PathVariable("userId") long userId)
	{
			userimpl.deleteUser(userId);
			return new ResponseEntity<Response>(new Response("user deleted",null,200),HttpStatus.OK);
	}
	/**
	 * Get User By id : get the user based upon user id in the table
	 * @param userId
	 * @return user
	 */
	@GetMapping("/getuserbyid/{userId}")
	public ResponseEntity<Response> getuserById(@PathVariable("userId") long userId)
	{
		return new ResponseEntity<Response>(new Response("welcome",userimpl.getUserById(userId),200),HttpStatus.OK);
	}
	/**
	 * Update Password : set new password for user
	 * @param pwddto
	 * @return response od update password
	 */
	@PutMapping("/updatepassword")
	public ResponseEntity<Response> updatePassword(@RequestBody UpdatePwdDto pwddto)
	{
		return new ResponseEntity<Response>(new Response("password updated successfully", userimpl.updatepwd(pwddto),200),HttpStatus.OK);
	}
	@PutMapping("/forgotpassword")
	public ResponseEntity<Response> forgotPassword(@RequestBody ForgotPwdDto forgotdto)
	{
		return new ResponseEntity<Response>(new Response("password updated and sent to mail successfully","your new pwd is:"+userimpl.forgotPwd(forgotdto),200),HttpStatus.OK);
	}
	
	@PostMapping
    public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file)
    {
        this.amazonS3ClientService.uploadFileToS3Bucket(file, true);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");

        return response;
    }

    @DeleteMapping
    public Map<String, String> deleteFile(@RequestParam("file_name") String fileName)
    {
        this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "file [" + fileName + "] removing request submitted successfully.");

        return response;
    }
	
}
