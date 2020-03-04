package com.bridgelabz.Fundoo.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.bridgelabz.Fundoo.Dto.UserDto;
import com.bridgelabz.Fundoo.Entity.UserEntity;
import com.bridgelabz.Fundoo.Repository.UserRepository;
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
	public boolean getUserByEmail(String email) {
		String emailId=userrepo.getUserByEmail(email);
		if(emailId==null)
			return false;
		return true;
	}

	@Override
	public List<UserEntity> getall()
	{
		return userrepo.getAllUsers();
	}
	@Override
	public UserEntity verify(String token) {
		long id=jwt.parseJWT(token);
		UserEntity user=getUserById(id);
		user.setVerifyEmail(true);
		userrepo.save(user);
		return user;
	}
	@Override
	public UserEntity getUserById(long id) {
		UserEntity user=userrepo.getUserById(id);
		if(user.getEmail()!=null)
		return user;
		return null;
	}
	@Override
	public boolean isIdPresent(long id) {
		UserEntity user=userrepo.getUserById(id);
		if(user.getEmail()!=null)
		return true;
		return false;
	}
	public void deleteUser(long userId) {
		userrepo.deleteUser(userId);
		
	}
}
