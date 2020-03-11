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
	
	@ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
	private List<LabelEntity> labels;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "noteId",referencedColumnName = "noteId")
	private List<NoteEntity> colaborators;
	

}
