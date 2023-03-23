package com.stresslesslibrary.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stresslesslibrary.bookservice.entities.Branch;


public interface BranchRepository extends JpaRepository<Branch, String> {

}
