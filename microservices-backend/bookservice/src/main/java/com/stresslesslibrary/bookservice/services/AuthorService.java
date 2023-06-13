package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.entities.Author;



public interface AuthorService {
	
	List<Author> findAll();
	
	Author getOne(int id);
	
	Author findByName(String name) throws Exception;
	
	Author save(Author author) throws Exception;

	List<Author> search(String keyword);

}
