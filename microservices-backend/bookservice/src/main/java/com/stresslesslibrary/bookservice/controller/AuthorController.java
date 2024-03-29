package com.stresslesslibrary.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.stresslesslibrary.bookservice.entities.Author;
import com.stresslesslibrary.bookservice.services.AuthorService;



@RestController
@RequestMapping("authors")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@GetMapping
	public ResponseEntity<List<Author>> findAll(){
		
		return ResponseEntity.ok().body(authorService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> findById(@PathVariable(value = "id") int id){
		
		try {
			return ResponseEntity.ok().body(authorService.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(defaultValue = "") String keyword){
			
					return ResponseEntity.ok().body(authorService.search(keyword));
		
	}
	
	@PostMapping
	public ResponseEntity<Author> createAuthor(@RequestBody Author author) throws Exception{
		System.out.println(author.toString());
		return ResponseEntity.ok().body(authorService.save(author));
	}
}
