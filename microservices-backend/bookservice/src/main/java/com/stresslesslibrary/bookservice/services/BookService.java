package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.dtos.BookDTO;
import com.stresslesslibrary.bookservice.entities.Book;


public interface BookService {
	
	List<Book> findAll();
	
	Book getOne(String isbn);
	
	List<Book> searchPatern(String keyword);
	
	Book saveBook(BookDTO book);
	
	Boolean setAuthor(int authorId, String isbn);
	
	Book updateBook(BookDTO book);

	Book update (Book book);

	List<Book> popularBooks();
}
