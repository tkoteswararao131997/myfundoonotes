package com.bridgelabz.Fundoo.Entity;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data
@Table(name="collaborators")
public class CollaboratorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long colabId;
	private String colabEmail;
	private LocalDateTime addedDate;
}
