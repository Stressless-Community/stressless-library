package com.stresslesslibrary.bookservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stresslesslibrary.bookservice.entities.Book;


public interface BookRepository extends JpaRepository<Book, String> {
	
	@Query("SELECT b FROM Book b WHERE b.title LIKE %:search%" 
			+ " OR b.isbn LIKE %:search%"
			+ " OR b.subtitle LIKE %:search%"
			+ " OR b.description LIKE %:search%")
	List<Book> search(@Param("search") String search);

	List<Book> findTop10ByOrderByRecordedDateDesc();

}
