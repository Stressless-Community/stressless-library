package com.stresslesslibrary.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stresslesslibrary.bookservice.entities.Publisher;
import com.stresslesslibrary.bookservice.services.PublisherService;

@RestController
@RequestMapping("publishers")
public class PublisherController {
	@Autowired
	private PublisherService publisherService;
	
	@GetMapping
	public ResponseEntity<List<Publisher>> findAll(){
		
		return ResponseEntity.ok().body(publisherService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Publisher> findById(@PathVariable(value = "id") int id){
		
		try {
			return ResponseEntity.ok().body(publisherService.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			
		}
	}
	
	@PostMapping
	public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher){
		
		return ResponseEntity.ok().body(publisherService.save(publisher));
		
	};
	
}
