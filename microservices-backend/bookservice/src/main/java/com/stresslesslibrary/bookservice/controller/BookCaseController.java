package com.stresslesslibrary.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stresslesslibrary.bookservice.dtos.BookReport;
import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.BookCase;
import com.stresslesslibrary.bookservice.services.BookCaseService;


@RestController
@RequestMapping("bookcases")
public class BookCaseController {

	@Autowired
	private BookCaseService bookCaseService;
	
	@GetMapping
	public ResponseEntity<List<BookCase>> findAll(){
		
		return ResponseEntity.ok().body(bookCaseService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookCase> findById(@PathVariable(value = "id") String id){
		
		try {
			return ResponseEntity.ok().body(bookCaseService.findOne(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			
		}
	}

	@GetMapping("/{id}/books")
	public ResponseEntity<List<Book>> getBooksByBookCase(@PathVariable(value = "id") String id){
		
		try {
			return ResponseEntity.ok().body(bookCaseService.getBooks(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			
		}
	}

	@GetMapping("/report")
	public ResponseEntity<BookReport> report() {
		return ResponseEntity.ok().body(bookCaseService.getReport());
	}
}
