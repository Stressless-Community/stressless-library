package com.stresslesslibrary.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stresslesslibrary.bookservice.entities.BookCase;

public interface BookCaseRepository extends JpaRepository<BookCase, String> {

}
