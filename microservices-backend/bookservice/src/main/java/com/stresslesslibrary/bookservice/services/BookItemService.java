package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.entities.BookItem;



public interface BookItemService {
	
	List<BookItem> findAll();
}
