package com.stresslesslibrary.bookservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.entities.Branch;

public interface BranchService {

	List<Branch> findAll();
	
	Branch getOne(String id);
	
	Branch save(Branch branch);
}
