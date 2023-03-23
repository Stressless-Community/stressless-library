package com.stresslesslibrary.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stresslesslibrary.bookservice.entities.BookCategory;


public interface BookCategoryRepository extends JpaRepository<BookCategory, String> {

}
