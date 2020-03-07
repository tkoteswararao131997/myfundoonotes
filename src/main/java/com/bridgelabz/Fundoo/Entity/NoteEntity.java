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
import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
@Entity
@Table(name="notes")
public class NoteEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noteId;
	@NotBlank(message = "title must not be blank")
	private String title;
	private String description;
	private String color;
	private boolean isPinned;
	private LocalDateTime reminde;
	private boolean isArchieve;
	private LocalDateTime createDate;
	private LocalDateTime UpdateDate;
	private boolean isTrashed;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<LabelEntity> labels;
	

}
