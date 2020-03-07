package com.bridgelabz.Fundoo.Entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
@Data
@Entity
@Table(name = "labels")
public class LabelEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long labelId;
	@NotNull
	private String labelName;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
}
