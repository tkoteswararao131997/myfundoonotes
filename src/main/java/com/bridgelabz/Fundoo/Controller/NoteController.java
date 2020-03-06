package com.bridgelabz.Fundoo.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.Fundoo.Dto.NoteDto;
import com.bridgelabz.Fundoo.Entity.NoteEntity;
import com.bridgelabz.Fundoo.Response.Response;
import com.bridgelabz.Fundoo.ServiceImpl.NoteServiceImpl;

@RestController
public class NoteController {
	@Autowired
	private NoteServiceImpl noteimpl;
	@PostMapping("/addnote/{token}")
	public ResponseEntity<Response> addNote(@Valid @RequestBody NoteDto notedto,@PathVariable("token") String token,BindingResult result)
	{
		if(result.hasErrors())
			return new ResponseEntity<Response>(new Response("invalid details",null,400),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Response>(new Response("note added",noteimpl.addNote(notedto,token),201),HttpStatus.CREATED);
	}
	
	@GetMapping("/getallnotes/{token}")
	public ResponseEntity<Response> getAllNotes(@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("your notes are",noteimpl.getAllNotes(token),200),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletenotes/{token}/{noteid}")
	public ResponseEntity<Response> deleteNoteById(@PathVariable("token") String token,@PathVariable("noteid") long noteid)
	{
		noteimpl.deleteNoteById(token,noteid);
		return new ResponseEntity<Response>(new Response("note was deleted",null,200),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteallnotes/{token}")
	public ResponseEntity<Response> deleteAllNotes(@PathVariable("token") String token)
	{
		noteimpl.deleteAllNotes(token);
		return new ResponseEntity<Response>(new Response("all notes were deleted",null,200),HttpStatus.OK);
	}

}
