package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.entities.BookItem;
import com.stresslesslibrary.bookservice.repositories.BookItemRepository;
import com.stresslesslibrary.bookservice.services.BookItemService;


@Service
public class BookItemServiceImpl implements BookItemService {
	
	@Autowired
	private BookItemRepository bookItemRepository;
	
	@Override
	public List<BookItem> findAll() {

		return bookItemRepository.findAll();
	}

}
