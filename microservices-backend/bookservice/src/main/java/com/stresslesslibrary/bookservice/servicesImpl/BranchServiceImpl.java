package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return branchRepository.findById(id).orElseThrow(
				//TODO exception handler
				);
	}

	@Override
	public Branch save(Branch branch) {
		return branchRepository.save(branch);
	}

}
