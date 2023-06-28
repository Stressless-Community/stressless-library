package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.BookCategory;



public interface BookCategoryService {
	
	List<BookCategory> findAll();
	
	BookCategory getOne(String id);
	
	BookCategory save(BookCategory bookCategory);

	List<Book> getBooks(String id);

	int getBookCount(String id);
}
