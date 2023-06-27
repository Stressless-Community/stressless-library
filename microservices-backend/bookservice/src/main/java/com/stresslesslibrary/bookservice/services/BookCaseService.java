package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.entities.BookCase;



public interface BookCaseService {
	
	List<BookCase> findAll();
	
	BookCase findOne(String id);
	
	BookCase save(BookCase bookCase);
	
}
