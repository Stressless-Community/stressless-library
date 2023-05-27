package com.stresslesslibrary.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.stresslesslibrary.bookservice.entities.BookImage;

@RepositoryRestResource
public interface BookImageRepository extends JpaRepository<BookImage, Integer> {
	
	@Query(value="SELECT im  FROM BookImage im WHERE im.name=:search")
	BookImage findByName(@Param("search") String search) throws Exception;

	boolean existsBookImageByName(String name);
	BookImage findBookImageByName(String name);
}
