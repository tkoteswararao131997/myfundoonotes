package com.bridgelabz.Fundoo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.Fundoo.Dto.LabelDto;
import com.bridgelabz.Fundoo.Entity.LabelEntity;
import com.bridgelabz.Fundoo.Entity.NoteEntity;
import com.bridgelabz.Fundoo.Response.Response;
import com.bridgelabz.Fundoo.ServiceImpl.LabelServiceImpl;

@RestController
@CrossOrigin("*")
public class LabelController {
	
	@Autowired
	private LabelServiceImpl labelimpl;
	/**
	 * Add Label : used to add label into user
	 * @param labeldto
	 * @param token
	 * @return response of label added into user
	 */
	@PostMapping("/addlabel")
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto labeldto,@RequestHeader("token") String token)
	{
		return new ResponseEntity<Response>(new Response("label created",labelimpl.createLabel(labeldto,token),201,"true"),HttpStatus.ACCEPTED);
	}
	/**
	 * Add Lable To Note : used to add label into note
	 * @param noteid
	 * @param token
	 * @param labelid
	 * @return response of label added into note
	 */
	@PutMapping("/addlabeltonote/{noteid}/{labelid}")
	public ResponseEntity<Response> addNoteToLabel(@PathVariable("noteid") Long noteid,@RequestHeader String token,@PathVariable Long labelid)
	{
		return new ResponseEntity<Response>(new Response("label added to note",labelimpl.addNoteToLabel(noteid,token,labelid),200,"true"),HttpStatus.OK);
	}
	/**
	 * Update Label : used to update the label
	 * @param labeldto
	 * @param token
	 * @param labelid
	 * @return response of updated label
	 */
	@PutMapping("/updatelabel/{labelid}")
	public ResponseEntity<Response> updateLabel(@RequestBody LabelDto labeldto,@RequestHeader String token,@PathVariable Long labelid,BindingResult result)
	{

		if(result.hasErrors())
		return new ResponseEntity<Response>(new Response("invalid details",null,400,"true"),HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Response>(new Response("label updated",labelimpl.updateLabel(labeldto,token,labelid),200,"true"),HttpStatus.OK);
	}
	/**
	 * Delete Label : used to delete the label 
	 * @param token
	 * @param labelid
	 * @return label deleted response
	 */
	@DeleteMapping("/deletelabel/{labelid}")
	public ResponseEntity<Response> deleteLabel(@RequestHeader String token,@PathVariable("labelid") Long labelid)
	{
		labelimpl.deleteLabel(token,labelid);
		return new ResponseEntity<Response>(new Response("label deleted",null,200,"true"),HttpStatus.OK);
	}
	
	@GetMapping("/getalllabels")
	public ResponseEntity<Response> getAllLabels(@RequestHeader String token)
	{
		return new ResponseEntity<Response>(new Response("your labels are",labelimpl.getAllLabels(token),200,"true"),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletelabelfromnote/{labelid}/{noteid}")
	public ResponseEntity<Response> deleteLabelFromNOte(@RequestHeader String token,@PathVariable Long labelid,@PathVariable("noteid") Long noteid)
	{
		labelimpl.deleteLabelFromNote(token,labelid,noteid);
		return new ResponseEntity<Response>(new Response("label deleted from note",null,200,"true"),HttpStatus.OK);
	}
	@GetMapping("/getnotesfromlabel/{labelid}")
	public ResponseEntity<Response> getNotesFromLabel(@RequestHeader String token,@PathVariable("labelid") Long labelid)
	{
		List<NoteEntity> notelist=labelimpl.getnotesfromlabel(token,labelid);
		return new ResponseEntity<Response>(new Response("label notes are",notelist,200,"true"),HttpStatus.OK);
	}
	@PostMapping("/createlabeladdnote/{noteid}")
	public ResponseEntity<Response> createLabelAddNote(@RequestHeader String token,@RequestBody LabelDto labeldto,@PathVariable("noteid") Long noteid)
	{
		return new ResponseEntity<Response>(new Response("label created and added",labelimpl.createLabelAddNote(token,labeldto,noteid),200,"true"),HttpStatus.OK);
	}
}
