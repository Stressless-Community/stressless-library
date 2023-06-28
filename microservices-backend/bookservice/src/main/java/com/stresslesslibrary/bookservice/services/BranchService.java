package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.Branch;


public interface BranchService {

	List<Branch> findAll();

	List<Branch> search(String keyword);
	
	Branch getOne(String id);
	
	Branch save(Branch branch);

	List<Book> getBooks(String id);
	
}
