package com.stresslesslibrary.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stresslesslibrary.bookservice.entities.Branch;
import com.stresslesslibrary.bookservice.services.BranchService;


@RestController
@RequestMapping("branches")
public class BranchController {

	@Autowired
	private BranchService branchService;
	
	@GetMapping
	public ResponseEntity<List<Branch>> findAll(){
		
		return ResponseEntity.ok().body(branchService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Branch> findById(@PathVariable(value = "id") String id){
		
		try {
			return ResponseEntity.ok().body(branchService.getOne(id));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
			
		}
	}

	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam(defaultValue = "") String keyword){
			if (keyword=="" || branchService.search(keyword).size()==0 ){
				return ResponseEntity.ok().body(branchService.findAll());
			}
			else{
					List <Branch> result= branchService.search(keyword);
					return ResponseEntity.ok().body(result);
			}	
		
	}
}
