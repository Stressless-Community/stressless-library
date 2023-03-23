package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.entities.Author;
import com.stresslesslibrary.bookservice.repositories.AuthorRepository;
import com.stresslesslibrary.bookservice.services.AuthorService;


@Service 
public class AuthorServiceImpl implements AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public List<Author> findAll() {
		return authorRepository.findAll();
	}

	@Override
	public Author getOne(int id) {
		return authorRepository.findById(id).orElseThrow(
				//TODO Exception handler
				);
	}

	@Override
	public Author findByName(String name) throws Exception {
		try {
			return authorRepository.findByName(name);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public Author save(Author author)  {
		Author au=new Author();
		try {
			au =findByName(author.getName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(au!=null) {
			return au;
		}
		return authorRepository.save(author);
		
	}

}
