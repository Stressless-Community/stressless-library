package com.stresslesslibrary.bookservice.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.entities.BookIndex;
import com.stresslesslibrary.bookservice.repositories.BookIndexRepository;
import com.stresslesslibrary.bookservice.services.BookIndexService;

@Service
public class BookIndexServicImpl implements BookIndexService {

    @Autowired
    private BookIndexRepository bookIndexRepository;

    @Override
    public List<BookIndex> findAll() {
       return bookIndexRepository.findAll();
    }

    @Override
    public BookIndex getOne(int id) {
        return bookIndexRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookIndex> search(String keyword) {
        return bookIndexRepository.search(keyword);
    }

    @Override
    public BookIndex save(BookIndex bookIndex) {
        return bookIndexRepository.save(bookIndex);
    }
    
}
