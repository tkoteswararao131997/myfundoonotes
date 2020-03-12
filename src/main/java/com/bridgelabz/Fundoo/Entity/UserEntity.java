package com.bridgelabz.Fundoo.Entity;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="users")
public class UserEntity{
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
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="userid")
	private List<NoteEntity> notes;
	
	@OneToMany(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
	@JoinColumn(name="userid")
	private List<LabelEntity> labels;
	
//	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY,mappedBy = "collaborators")
//	private List<NoteEntity> collaboratorNotes;
}
