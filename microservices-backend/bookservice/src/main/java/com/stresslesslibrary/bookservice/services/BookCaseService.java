package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.dtos.BookReport;
import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.BookCase;



public interface BookCaseService {
	
	List<BookCase> findAll();
	
	BookCase findOne(String id);
	
	BookCase save(BookCase bookCase);

	List<Book> getBooks(String id);
	BookReport getReport();
	
}
