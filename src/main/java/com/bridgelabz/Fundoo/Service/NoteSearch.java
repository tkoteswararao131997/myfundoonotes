package com.bridgelabz.Fundoo.Service;

import java.io.IOException;
import java.util.List;

import com.bridgelabz.Fundoo.Entity.NoteEntity;

public interface NoteSearch {
	
	public String createNote(NoteEntity note) throws IOException ;
	public NoteEntity findNoteById(String id) throws Exception;

	public String updateNote(NoteEntity note) throws Exception;

	public String deleteNote(String id) throws IOException;
	
	 List<NoteEntity> searchByTitle(String text);

}
