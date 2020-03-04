package com.bridgelabz.Fundoo.Entity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userid;
	private String name;
	private String password;
	private String mobileNumber;
	private String email;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private boolean isVerifyEmail=false;
}
