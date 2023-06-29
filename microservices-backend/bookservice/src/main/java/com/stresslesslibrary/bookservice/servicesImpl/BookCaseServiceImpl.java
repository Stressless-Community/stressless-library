package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.entities.BookCase;
import com.stresslesslibrary.bookservice.repositories.BookCaseRepository;
import com.stresslesslibrary.bookservice.services.BookCaseService;



@Service 
public class BookCaseServiceImpl implements BookCaseService {
	
	@Autowired
	private BookCaseRepository bookCaseRepository;
	
	@Override
	public List<BookCase> findAll() {
		return bookCaseRepository.findAll();
	}

	@Override
	public BookCase findOne(String id) {
		
		return bookCaseRepository.findById(id).orElseThrow(
				//TODO Exception handler
				);
	}

	@Override
	public BookCase save(BookCase bookCase) {
		return bookCaseRepository.save(bookCase);
	}
	
}
