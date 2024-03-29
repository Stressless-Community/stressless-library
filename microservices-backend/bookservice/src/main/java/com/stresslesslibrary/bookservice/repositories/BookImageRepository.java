package com.stresslesslibrary.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stresslesslibrary.bookservice.entities.BookImage;

@RepositoryRestResource
public interface BookImageRepository extends JpaRepository<BookImage, Integer> {
	
	Boolean existsBookImageByName(String name);
	
	@Query(value="SELECT image  FROM BookImage image WHERE image.name=:name")
	BookImage findBookImageByName(@Param("name") String name);
}
