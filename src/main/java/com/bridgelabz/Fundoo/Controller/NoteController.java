package com.bridgelabz.Fundoo.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.Fundoo.Dto.NoteDto;
import com.bridgelabz.Fundoo.Dto.UpdateNoteDto;
import com.bridgelabz.Fundoo.Entity.NoteEntity;
import com.bridgelabz.Fundoo.Response.Response;
import com.bridgelabz.Fundoo.ServiceImpl.NoteServiceImpl;

@RestController
public class NoteController {
	@Autowired
	private NoteServiceImpl noteimpl;
	
	/**
	 * Add Note : used to add note to user
	 * @param notedto
	 * @param token
	 * @param result
	 * @return note added or not response
	 */
	@PostMapping("/addnote/{token}")
	public ResponseEntity<Response> addNote(@Valid @RequestBody NoteDto notedto,@PathVariable("token") String token,BindingResult result)
	{
		if(result.hasErrors())
			return new ResponseEntity<Response>(new Response("invalid details",null,400),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Response>(new Response("note added",noteimpl.addNote(notedto,token),201),HttpStatus.CREATED);
	}
	
	/**
	 * Get All Notes:used to get all notes by that user
	 * @param token
	 * @return display all the notes in response
	 */
	@GetMapping("/getallnotes/{token}")
	public ResponseEntity<Response> getAllNotes(@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("your notes are",noteimpl.getAllNotes(token),200),HttpStatus.OK);
	}
	
	/**
	 * Delete Note:used to delete note based upon id in that user
	 * @param token
	 * @param noteid
	 * @return deleted note response
	 */
	@DeleteMapping("/deletenotes/{token}/{noteid}")
	public ResponseEntity<Response> deleteNoteById(@PathVariable("token") String token,@PathVariable("noteid") long noteid)
	{
		noteimpl.deleteNoteById(token,noteid);
		return new ResponseEntity<Response>(new Response("note was deleted",null,200),HttpStatus.OK);
	}
	
	/**
	 * Delete All Notes : used to delete all notes of that user
	 * @param token
	 * @return deleted all notes
	 */
	@DeleteMapping("/deleteallnotes/{token}")
	public ResponseEntity<Response> deleteAllNotes(@PathVariable("token") String token)
	{
		noteimpl.deleteAllNotes(token);
		return new ResponseEntity<Response>(new Response("all notes were deleted",null,200),HttpStatus.OK);
	}
	/**
	 * Update Note : used to update the note content
	 * @param updatenotedto
	 * @param token
	 * @param noteid
	 * @return display updated note
	 */
	@PutMapping("/updatenote/{token}/{noteid}")
	public ResponseEntity<Response> updateNote(@RequestBody UpdateNoteDto updatenotedto,@PathVariable("token") String token,@PathVariable("noteid") long noteid)
	{
		NoteEntity note=noteimpl.updateNote(token,noteid,updatenotedto);
		return new ResponseEntity<Response>(new Response(note.getTitle()+"note was updated",note,200),HttpStatus.OK);
	}
	
	/**
	 * pin note : used to pin the note
	 * @param token
	 * @param noteid
	 * @return to display the note
	 */
	@PutMapping("/ispinnote/{token}/{noteid}")
	public ResponseEntity<Response> isPinNote(@PathVariable("token") String token,@PathVariable("noteid") long noteid)
	{
		NoteEntity note=noteimpl.isPinNote(token,noteid);
		return new ResponseEntity<Response>(new Response(note.getTitle()+" was pinned",note,200),HttpStatus.OK);
	}
	/**
	 * Archieve Note : used to archieve the note
	 * @param token
	 * @param noteid
	 * @return to display the note
	 */
	@PutMapping("/isarchieve/{token}/{noteid}")
	public ResponseEntity<Response> isArchieve(@PathVariable("token") String token,@PathVariable("noteid") long noteid)
	{
		NoteEntity note=noteimpl.isArchieveNote(token,noteid);
		return new ResponseEntity<Response>(new Response(note.getTitle()+" was archieved",note,200),HttpStatus.OK);
	}
	/**
	 * Trash Note : used to store note in trash
	 * @param token
	 * @param noteid
	 * @return to display the note
	 */
	@PutMapping("/istrashed/{token}/{noteid}")
	public ResponseEntity<Response> isTrashed(@PathVariable("token") String token,@PathVariable("noteid") long noteid)
	{
		NoteEntity note=noteimpl.isTrashed(token,noteid);
		return new ResponseEntity<Response>(new Response(note.getTitle()+" was archieved",note,200),HttpStatus.OK);
	}
	/**
	 * Remind Me : used to set reminder
	 * @param token
	 * @param noteid
	 * @return remind response
	 */
	@PutMapping("/remindme/{token}/{noteid}")
	public ResponseEntity<Response> remindMe(@RequestBody LocalDateTime reminderDate,@PathVariable("token") String token,@PathVariable("noteid") long noteid)
	{
		NoteEntity note=noteimpl.remindMe(token,noteid,reminderDate);
		return new ResponseEntity<Response>(new Response(note.getReminde()+" was set",note,200),HttpStatus.OK);
	}
	/**
	 * Get All Pin-Notes : used to get all pinned notes
	 * @param token
	 * @return pinned notes
	 */
	@GetMapping("/getallpins/{token}")
	public ResponseEntity<Response> getAllPinNotes(@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("your notes are",noteimpl.getAllPinNotes(token),200),HttpStatus.OK);
	}
	/**
	 * Get All Archieve-Notes : used to get all archieved notes
	 * @param token
	 * @return archieved notes
	 */
	@GetMapping("/getallarchieves/{token}")
	public ResponseEntity<Response> getAllArchieveNotes(@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("your notes are",noteimpl.getAllArchieveNotes(token),200),HttpStatus.OK);
	}
			
}