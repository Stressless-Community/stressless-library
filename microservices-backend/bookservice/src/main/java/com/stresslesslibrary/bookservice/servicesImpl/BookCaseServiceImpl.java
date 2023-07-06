package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.dtos.BookCaseCount;
import com.stresslesslibrary.bookservice.dtos.BookReport;
import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.BookCase;
import com.stresslesslibrary.bookservice.entities.BookCategory;
import com.stresslesslibrary.bookservice.repositories.BookCaseRepository;
import com.stresslesslibrary.bookservice.services.BookCaseService;
import com.stresslesslibrary.bookservice.services.BookService;



@Service 
public class BookCaseServiceImpl implements BookCaseService {
	
	@Autowired
	private BookCaseRepository bookCaseRepository;
	@Autowired
	private BookService bookService;
	@Override
	public List<BookCase> findAll() {
		return bookCaseRepository.findAll();
	}

	@Override
	public BookCase findOne(String id) {
		
		return bookCaseRepository.findById(id).orElse(
				null
				);
	}

	@Override
	public BookCase save(BookCase bookCase) {
		return bookCaseRepository.save(bookCase);
	}

	@Override
	public List<Book> getBooks(String id) {
			try {
				return findOne(id).getBooks();
			} catch (Exception e) {
				return null;
			} 
	}

	@Override
	public BookReport getReport() {
		BookReport report = new BookReport();
		for(  BookCase bookcase : findAll()){
			report.addBookCaseCount(new BookCaseCount(bookcase.getId(), bookcase.getName(), bookcase.getBooks().size())); 
		}
		report.setTotalBooks(bookService.findAll().size());
		return report;
	}

	@Override
	public int getBookCount(String id) {
		return getBooks(id).size();
	}

	@Override
	public List<BookCategory> getCategories(String id) {
		return findOne(id).getCategories();
	}

	
}
