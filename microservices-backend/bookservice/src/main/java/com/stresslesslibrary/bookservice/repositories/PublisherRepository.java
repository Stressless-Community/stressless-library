package com.stresslesslibrary.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stresslesslibrary.bookservice.entities.Publisher;


public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
	
	Publisher findByName(String name) throws Exception;
}
