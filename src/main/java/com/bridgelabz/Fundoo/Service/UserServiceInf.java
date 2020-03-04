package com.bridgelabz.Fundoo.Service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.Fundoo.Dto.UserDto;
import com.bridgelabz.Fundoo.Entity.UserEntity;

public interface UserServiceInf {
	UserEntity registerUser(UserDto dto);
	boolean getUserByEmail(String email);
	List<UserEntity> getall();

}
