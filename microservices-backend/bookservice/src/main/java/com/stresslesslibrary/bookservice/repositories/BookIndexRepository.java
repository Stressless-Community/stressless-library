package com.stresslesslibrary.bookservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.stresslesslibrary.bookservice.entities.Author;
import com.stresslesslibrary.bookservice.entities.BookIndex;

public interface BookIndexRepository extends JpaRepository<BookIndex, Integer > {
	
	@Query(value="SELECT index  FROM BookIndex index WHERE index.name=:search")
	 BookIndex findName(@Param("search") String search) throws Exception;
	
	@Query(value="SELECT au  FROM Author au WHERE au.name LIKE %:search%")
	List<Author> search(@Param("search") String search);
}
