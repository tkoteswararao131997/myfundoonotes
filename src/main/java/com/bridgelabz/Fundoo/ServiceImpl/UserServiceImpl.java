package com.bridgelabz.Fundoo.ServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.Fundoo.Dto.UserDto;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Exception.UserExceptions;
import com.bridgelabz.Fundoo.Repository.UserRepository;
import com.bridgelabz.Fundoo.Response.UserResponse;
import com.bridgelabz.Fundoo.Service.UserServiceInf;
import com.bridgelabz.Fundoo.Utility.JwtOperations;
@Service
public class UserServiceImpl implements UserServiceInf {
	@Autowired
	private UserRepository userrepo;
	private JwtOperations jwt=new JwtOperations();
	@Override
	public UserEntity registerUser(UserDto dto)
	{
		boolean b=userrepo.getUserByEmail(dto.getEmail()).isPresent();
		if(b==true)
			throw new UserExceptions("email already exists",HttpStatus.NOT_ACCEPTABLE,null);
		UserEntity entity=new UserEntity();
		BeanUtils.copyProperties(dto, entity);
		entity.setCreateDate(LocalDateTime.now());
		entity.setUpdateDate(LocalDateTime.now());
		userrepo.save(entity);
		String body="http://localhost:8080/verifyemail/"+jwt.jwtToken(entity.getUserid());
		jwt.sendEmail(entity.getEmail(),"verification email",body);
		return entity;
	}
	@Override
	public UserEntity loginUser(String email, String password) {
		return userrepo.getUserByEmail(email).orElseThrow(() -> new UserExceptions("login failed",HttpStatus.NOT_FOUND,null));
		
		
		
	}
	@Override
	public List<UserEntity> getall()
	{
		return userrepo.getAllUsers().orElseThrow(() -> new UserExceptions("no users present", HttpStatus.NOT_FOUND,null));
	}
	@Override
	public UserEntity getUserByEmail(String email) {
	UserEntity user=userrepo.getUserByEmail(email).orElseThrow(() -> new UserExceptions("email not exists",HttpStatus.BAD_REQUEST,null));
	return user;
	}
	
	@Override
	public UserEntity verify(String token) {
		long id=jwt.parseJWT(token);
		UserEntity user=userrepo.getUserById(id).orElseThrow(() -> new UserExceptions("user not exists",HttpStatus.BAD_REQUEST,id));
		user.setVerifyEmail(true);
		userrepo.save(user);
		return user;
	}
	@Override
	public UserEntity getUserById(long id) {
		UserEntity user=userrepo.getUserById(id).orElseThrow(() -> new UserExceptions("user not exists",HttpStatus.BAD_REQUEST,id));
		if(user.getEmail()!=null)
		return user;
		return null;
	}
	@Override
	public boolean isIdPresent(long id) {
		UserEntity user=userrepo.getUserById(id).orElseThrow(() -> new UserExceptions("user not exists",HttpStatus.BAD_REQUEST,id));
		if(user.getEmail()!=null)
		return true;
		return false;
	}
	public Integer deleteUser(long userId) {
		return userrepo.deleteUser(userId).orElseThrow(() -> new UserExceptions("id not exists",HttpStatus.NOT_FOUND,userId));
		
	}
	@Override
	public boolean isEmailExists(String email) {
			String isemail=userrepo.isEmailExists(email);
			if(isemail==null)
				return false;
			return true;
	}
	
}
