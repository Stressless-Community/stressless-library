package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.NotFound;
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
	public Publisher findByName(String name){
		return publisherRepository.findPublisherByName(name);
	}

	@Override
	public Publisher save(Publisher publisher) {
		if(publisherRepository.existsPublisherByName(publisher.getName())){
			System.out.print("Publisher exist");
			return  findByName(publisher.getName());
		}else{
			return publisherRepository.save(publisher);
		}

	}

	@Override
	public boolean existsByName(String name) {
		return publisherRepository.existsPublisherByName(name);
	}

}
