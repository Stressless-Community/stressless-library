package com.stresslesslibrary.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stresslesslibrary.bookservice.entities.BookItem;
import com.stresslesslibrary.bookservice.services.BookItemService;

@RestController
@RequestMapping("bookitems")
public class BookItemController {
	@Autowired
	private BookItemService bookItemService;
	
	@GetMapping
	public ResponseEntity<List<BookItem>> findAll(){
		
		return ResponseEntity.ok().body(bookItemService.findAll());
	}
	
}
