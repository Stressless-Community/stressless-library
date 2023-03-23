package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.entities.Publisher;
import com.stresslesslibrary.bookservice.repositories.PublisherRepository;
import com.stresslesslibrary.bookservice.services.PublisherService;



@Service
public class PublisherServiceImpl implements PublisherService {
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Override
	public List<Publisher> findAll() {
		return publisherRepository.findAll();
	}

	@Override
	public Publisher getOne(int id) {
		return publisherRepository.findById(id).orElseThrow(
				//TODO Exception handler
				);
		
	}

	@Override
	public Publisher findByName(String name) throws Exception{
		//TODO 
		return publisherRepository.findByName(name);
	}

	@Override
	public Publisher save(Publisher publisher) {
		
		return publisherRepository.save(publisher);
	}

}
