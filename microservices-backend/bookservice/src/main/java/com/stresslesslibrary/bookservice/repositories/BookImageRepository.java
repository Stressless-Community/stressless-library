package com.stresslesslibrary.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stresslesslibrary.bookservice.entities.BookImage;

@RepositoryRestResource
public interface BookImageRepository extends JpaRepository<BookImage, Integer> {
	
	Boolean existsBookImageByName(String name);

	BookImage findBookImageByName(String name);
}
