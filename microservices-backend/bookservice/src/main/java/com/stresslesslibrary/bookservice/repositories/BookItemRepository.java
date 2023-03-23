package com.stresslesslibrary.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stresslesslibrary.bookservice.entities.BookItem;



public interface BookItemRepository extends JpaRepository<BookItem, Long> {
	
}
