package com.stresslesslibrary.bookservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stresslesslibrary.bookservice.entities.Branch;


public interface BranchRepository extends JpaRepository<Branch, String> {

    @Query(value="SELECT b  FROM Branch b WHERE b.id LIKE %:search%")
	List<Branch> searchById(@Param("search") String search);
}
