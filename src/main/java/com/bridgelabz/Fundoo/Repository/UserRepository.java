package com.bridgelabz.Fundoo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
