package com.bridgelabz.Fundoo.Service;

import java.util.List;

import com.bridgelabz.Fundoo.Dto.NoteDto;
import com.bridgelabz.Fundoo.Entity.NoteEntity;

public interface NoteServiceInf {
	NoteEntity addNote(NoteDto notedto,String token);

	List<NoteEntity> getAllNotes(String token);

	void deleteNoteById(String token, long noteid);

	void deleteAllNotes(String token);
}
