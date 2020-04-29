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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.Fundoo.Dto.CollaboratorDto;
import com.bridgelabz.Fundoo.Response.Response;
import com.bridgelabz.Fundoo.ServiceImpl.CollaboratorServiceImpl;

@RestController
public class CollaboratorController {
	@Autowired
	private CollaboratorServiceImpl colabimpl;
	
	@PostMapping("/addcollaborator/{noteid}")
	public ResponseEntity<Response> addCollaborator(@RequestBody String colabEmail,@RequestHeader String token,@PathVariable("noteid") long noteid)
	{
		return new ResponseEntity<Response>(new Response("collaborator added",colabimpl.addColabToNote(colabEmail, token, noteid),201,"true"),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletecollaborator/{noteid}/{colabEmail}")
	public ResponseEntity<Response> deleteCollaborator(@PathVariable("colabEmail") String colabEmail,@RequestHeader String token,@PathVariable("noteid") long noteid)
	{
		System.out.println("in controller"+colabEmail);
		colabimpl.deleteColabFromNote(colabEmail, token, noteid);
		return new ResponseEntity<Response>(new Response("collaborator deleted",colabEmail,200,"true"),HttpStatus.OK);
	}
	
	@GetMapping("/getallcollaborators/{noteid}")
	public ResponseEntity<Response> getAllCollaborators(@RequestHeader String token,@PathVariable("noteid") long noteid)
	{
		return new ResponseEntity<Response>(new Response("note collaborators are",colabimpl.getAllColabs(token, noteid),200,"true"),HttpStatus.OK);
	}
	@GetMapping("/getcollaborator/{colabEmail}")
	public ResponseEntity<Response> getCollaborator(@PathVariable("colabEmail") String colabEmail,@RequestHeader String token)
	{
		return new ResponseEntity<Response>(new Response("collaborator is",colabimpl.getColab(token, colabEmail),200,"true"),HttpStatus.OK);

	}
}
