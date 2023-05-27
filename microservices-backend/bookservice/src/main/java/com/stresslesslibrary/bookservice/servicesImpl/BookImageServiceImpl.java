package com.stresslesslibrary.bookservice.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stresslesslibrary.bookservice.entities.BookImage;
import com.stresslesslibrary.bookservice.repositories.BookImageRepository;
import com.stresslesslibrary.bookservice.services.BookImageService;

@Service
public class BookImageServiceImpl implements BookImageService {

    @Autowired
    public BookImageRepository bookImageRepo;


    @Override
    public BookImage save(BookImage bookImage) {
        if(bookImageRepo.existsBookImageByName(bookImage.getName())){
            return bookImageRepo.findBookImageByName(bookImage.getName());
        }else{
            return bookImageRepo.save(bookImage);
        }
        
    }
    
}
