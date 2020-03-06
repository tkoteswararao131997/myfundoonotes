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

import com.bridgelabz.Fundoo.Dto.LoginDto;
import com.bridgelabz.Fundoo.Dto.UpdatePwdDto;
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
	private BCryptPasswordEncoder pwdencoder=new BCryptPasswordEncoder();
	private JwtOperations jwt=new JwtOperations();
	@Override
	public UserEntity registerUser(UserDto dto)
	{
		isEmailExists(dto.getEmail());
		UserEntity entity=new UserEntity();
		BeanUtils.copyProperties(dto, entity);
		entity.setCreateDate(LocalDateTime.now());
		entity.setUpdateDate(LocalDateTime.now());
		entity.setPassword(pwdencoder.encode(entity.getPassword()));
		userrepo.save(entity);
		String body="http://localhost:8080/verifyemail/"+jwt.jwtToken(entity.getUserid());
		jwt.sendEmail(entity.getEmail(),"verification email",body);
		return entity;
	}
	@Override
	public UserEntity loginUser(LoginDto dto) {
		UserEntity user=userrepo.getUserByEmail(dto.getEmail()).orElseThrow(() -> new UserExceptions("login failed",HttpStatus.NOT_FOUND,null));
		boolean ispwd=pwdencoder.matches(dto.getPassword(),user.getPassword());
		if(ispwd==false || user.isVerifyEmail()==false)
			throw new UserExceptions("login failed",HttpStatus.NOT_FOUND,null);
		return user;
		
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
		return userrepo.getUserById(id).orElseThrow(() -> new UserExceptions("user not exists",HttpStatus.BAD_REQUEST,id));
	}
	@Override
	public boolean isIdPresent(long id) {
		UserEntity user=userrepo.getUserById(id).orElseThrow(() -> new UserExceptions("user not exists",HttpStatus.BAD_REQUEST,id));
		if(user.getEmail()!=null)
		return true;
		return false;
	}
	public void deleteUser(long userId) {
		getUserById(userId);
		userrepo.deleteUser(userId);
		
	}
	@Override
	public boolean isEmailExists(String email) {
		boolean b=userrepo.isEmailExists(email).isPresent();
		if(b==true)
			throw new UserExceptions("email already exists",HttpStatus.NOT_ACCEPTABLE,null);
		return false;
	}
	@Override
	public UserEntity updatepwd(UpdatePwdDto pwddto) {
		UserEntity user=getUserByEmail(pwddto.getEmail());
		if((pwddto.getNewpassword().equals(pwddto.getConformpassword()) && (pwdencoder.matches(pwddto.getOldpassword(),user.getPassword()))))
		{
			user.setPassword(pwdencoder.encode(pwddto.getConformpassword()));
			userrepo.save(user);
			return user;
		}
		else {
			throw new UserExceptions("password not matching",HttpStatus.NOT_ACCEPTABLE,null);
		}
	}
	
}
