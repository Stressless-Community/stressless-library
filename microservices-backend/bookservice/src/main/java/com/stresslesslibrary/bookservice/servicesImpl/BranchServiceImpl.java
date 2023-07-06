package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.Branch;
import com.stresslesslibrary.bookservice.repositories.BranchRepository;
import com.stresslesslibrary.bookservice.services.BranchService;




@Service
public class BranchServiceImpl implements BranchService {
	@Autowired
	private BranchRepository branchRepository;
	
	@Override
	public List<Branch> findAll() {
		return branchRepository.findAll();
	}

	@Override
	public Branch getOne(String id) {
		return branchRepository.findById(id).orElse(
				null
				);
	}

	@Override
	public Branch save(Branch branch) {
		return branchRepository.save(branch);
	}

	@Override
	public List<Branch> search(String keyword) {
		
		return branchRepository.search(keyword);
	}

	@Override
	public List<Book> getBooks(String id) {
		// TODO Auto-generated method stub
		return getOne(id).getBooks();
	}

	@Override
	public int getBookCount(String id) {
		return getBooks(id).size();
	}

}
