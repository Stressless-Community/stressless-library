package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.entities.Branch;


public interface BranchService {

	List<Branch> findAll();
	
	Branch getOne(String id);
	
	Branch save(Branch branch);
	
	List<Branch> search(String keyword);
}
