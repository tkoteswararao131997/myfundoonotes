package com.bridgelabz.Fundoo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.Fundoo.Dto.LabelDto;
import com.bridgelabz.Fundoo.Entity.LabelEntity;
import com.bridgelabz.Fundoo.Response.Response;
import com.bridgelabz.Fundoo.ServiceImpl.LabelServiceImpl;

@RestController
public class LabelController {
	
	@Autowired
	private LabelServiceImpl labelimpl;
	/**
	 * Add Label : used to add label into user
	 * @param labeldto
	 * @param token
	 * @return response of label added into user
	 */
	@PostMapping("/addlabel/{token}")
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto labeldto,@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("label created",labelimpl.createLabel(labeldto,token),201),HttpStatus.ACCEPTED);
	}
	/**
	 * Add Lable To Note : used to add label into note
	 * @param noteid
	 * @param token
	 * @param labelid
	 * @return response of label added into note
	 */
	@PutMapping("/addlabeltonote/{noteid}/{token}/{labelid}")
	public ResponseEntity<Response> addNoteToLabel(@PathVariable("noteid") long noteid,@PathVariable("token") String token,long labelid)
	{
		return new ResponseEntity<Response>(new Response("label added",labelimpl.addNoteToLabel(noteid,token,labelid),200),HttpStatus.OK);
	}
	/**
	 * Update Label : used to update the label
	 * @param labeldto
	 * @param token
	 * @param labelid
	 * @return response of updated label
	 */
	@PutMapping("/updatelabel/{token}/{labelid}")
	public ResponseEntity<Response> updateLabel(@RequestBody LabelDto labeldto,@PathVariable("token") String token,long labelid)
	{
		return new ResponseEntity<Response>(new Response("label updated",labelimpl.updateLabel(labeldto,token,labelid),200),HttpStatus.OK);
	}
	/**
	 * Delete Label : used to delete the label 
	 * @param token
	 * @param labelid
	 * @return label deleted response
	 */
	@DeleteMapping("/deletelabel/{token}/{labelid}")
	public ResponseEntity<Response> deleteLabel(@PathVariable("token") String token,long labelid)
	{
		labelimpl.deleteLabel(token,labelid);
		return new ResponseEntity<Response>(new Response("label deleted",null,200),HttpStatus.OK);
	}
	
	@GetMapping("/getalllabels/{token}")
	public ResponseEntity<Response> getAllLabels(@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("your labels are",labelimpl.getAllLabels(token),200),HttpStatus.OK);
	}
	
	
}
