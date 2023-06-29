package com.stresslesslibrary.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stresslesslibrary.bookservice.entities.BookCategory;
import com.stresslesslibrary.bookservice.services.BookCategoryService;


@RestController
@RequestMapping("categories")
public class CategoryController {
	
	@Autowired
	private BookCategoryService bookCategory;
	
	@GetMapping
	public ResponseEntity<List<BookCategory>> findAll(){
		
		return ResponseEntity.ok().body(bookCategory.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookCategory> findById(@PathVariable(value = "id") String id){
		
		try {
			return ResponseEntity.ok().body(bookCategory.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			
		}
	}
	
}
