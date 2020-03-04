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

@Service
public class UserServiceImpl implements UserServiceInf {
	@Autowired
	private UserRepository userrepo;
	@Override
	public UserEntity registerUser(UserDto dto)
	{
		UserEntity entity=new UserEntity();
		BeanUtils.copyProperties(dto, entity);
		entity.setCreateDate(LocalDateTime.now());
		entity.setUpdateDate(LocalDateTime.now());
		return userrepo.save(entity);
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

}
