package com.stresslesslibrary.bookservice.repositories;

import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stresslesslibrary.bookservice.entities.Publisher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

	Publisher findPublisherByName(String name);
	boolean existsPublisherByName(String name);
}
