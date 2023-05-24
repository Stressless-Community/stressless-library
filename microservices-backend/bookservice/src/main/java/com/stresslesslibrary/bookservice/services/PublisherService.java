package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.entities.Publisher;


public interface PublisherService {
	
	List<Publisher> findAll();
	
	Publisher getOne(int id);
	
	Publisher findByName(String name);
	
	Publisher save(Publisher publisher);

	boolean existsByName(String name);
}
