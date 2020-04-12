package com.bridgelabz.Fundoo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.Fundoo.Dto.CollaboratorDto;
import com.bridgelabz.Fundoo.Response.Response;
import com.bridgelabz.Fundoo.ServiceImpl.CollaboratorServiceImpl;

@RestController
public class CollaboratorController {
	@Autowired
	private CollaboratorServiceImpl colabimpl;
	
	@PostMapping("/addcollaborator/{token}/{noteid}")
	public ResponseEntity<Response> addCollaborator(@RequestBody CollaboratorDto colabemail,@PathVariable("token") String token,@PathVariable("noteid") long noteid,BindingResult result)
	{

		if(result.hasErrors())
		return new ResponseEntity<Response>(new Response("invalid details",null,400,"true"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Response>(new Response("collaborator added",colabimpl.addColabToNote(colabemail, token, noteid),201,"true"),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletecollaborator/{token}/{noteid}")
	public ResponseEntity<Response> deleteCollaborator(@RequestBody CollaboratorDto colabemail,@PathVariable("token") String token,@PathVariable("noteid") long noteid,BindingResult result)
	{

		if(result.hasErrors())
		return new ResponseEntity<Response>(new Response("invalid details",null,400,"true"),HttpStatus.BAD_REQUEST);
		colabimpl.deleteColabFromNote(colabemail, token, noteid);
		return new ResponseEntity<Response>(new Response("collaborator deleted",null,200,"true"),HttpStatus.OK);
	}
	
	@GetMapping("/getallcollaborators/{token}/{noteid}")
	public ResponseEntity<Response> getAllCollaborators(@PathVariable("token") String token,@PathVariable("noteid") long noteid)
	{
		return new ResponseEntity<Response>(new Response("note collaborators are",colabimpl.getAllColabs(token, noteid),200,"true"),HttpStatus.OK);
	}
}
