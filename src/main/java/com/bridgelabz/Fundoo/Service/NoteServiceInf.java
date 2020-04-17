package com.bridgelabz.Fundoo.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.bridgelabz.Fundoo.Dto.NoteDto;
import com.bridgelabz.Fundoo.Dto.UpdateNoteDto;
import com.bridgelabz.Fundoo.Entity.NoteEntity;

public interface NoteServiceInf {
	NoteEntity addNote(NoteDto notedto,String token);

	List<NoteEntity> getAllNotes(String token);

	void deleteNoteById(String token, long noteid);

	void deleteAllNotes(String token);
	
	NoteEntity updateNote(String token, long noteid, UpdateNoteDto updatenotedto);

	NoteEntity isPinNote(String token, long noteid);

	NoteEntity isArchieveNote(String token, long noteid);

	NoteEntity isTrashed(String token, long noteid);
	
	NoteEntity remindMe(String token, long noteid, String reminderDate);
}
