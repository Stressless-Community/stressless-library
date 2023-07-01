package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.BookCategory;
import com.stresslesslibrary.bookservice.repositories.BookCategoryRepository;
import com.stresslesslibrary.bookservice.services.BookCategoryService;


@Service
public class BookCategoryServiceImpl implements BookCategoryService {
	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	
	@Override
	public List<BookCategory> findAll() {
		
		return bookCategoryRepository.findAll();
	}

	@Override
	public BookCategory getOne(String id) {
		return bookCategoryRepository.findById(id).orElse(
				null
				);
	}

	@Override
	public BookCategory save(BookCategory bookCategory) {
		return bookCategoryRepository.save(bookCategory);
	}

	@Override
	public List<Book> getBooks(String id) {
		if(getOne(id)!=null){
			return getOne(id).getBooks();
		}
		return null;
		
			
	}

	@Override
	public int getBookCount(String id) {
		return getBooks(id).size();
	}

}
