package com.bridgelabz.Fundoo.Repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import com.bridgelabz.Fundoo.Entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
	
	@Query(value="select * from users",nativeQuery=true)
	List<UserEntity> getAllUsers();
	
	@Query(value = "select email from users where email=?1",nativeQuery = true)
	String getUserByEmail(String email);
	
	@Query(value = "select * from users where userid=?1",nativeQuery = true)
	UserEntity getUserById(long id);
	@Modifying
	
	@Transactional 
	@Query(value = "delete from users where userid=?1",nativeQuery = true)
	void deleteUser(@RequestParam long userId);
	
}
