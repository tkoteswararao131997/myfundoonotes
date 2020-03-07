package com.bridgelabz.Fundoo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.Fundoo.Dto.LabelDto;
import com.bridgelabz.Fundoo.Response.Response;
import com.bridgelabz.Fundoo.ServiceImpl.LabelServiceImpl;

@RestController
public class LabelController {
	
	@Autowired
	private LabelServiceImpl labelimpl;
	@PostMapping("/addlabel/{token}")
	public ResponseEntity<Response> createLabel(@RequestBody LabelDto labeldto,@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("label created",labelimpl.createLabel(labeldto,token),201),HttpStatus.ACCEPTED);
	}
	@PutMapping("/addlabeltonote/{noteid}/{token}")
	public ResponseEntity<Response> addLabelToNote(@PathVariable("noteid") long noteid,@PathVariable("token") String token)
	{
		return new ResponseEntity<Response>(new Response("label added",labelimpl.addLabelToNote(noteid,token),200),HttpStatus.OK);
	}
}
