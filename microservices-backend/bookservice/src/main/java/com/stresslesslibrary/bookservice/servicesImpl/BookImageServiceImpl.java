package com.stresslesslibrary.bookservice.servicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.stresslesslibrary.bookservice.entities.BookImage;
import com.stresslesslibrary.bookservice.repositories.BookImageRepository;
import com.stresslesslibrary.bookservice.services.BookImageService;

@Service
public class BookImageServiceImpl implements BookImageService {

    @Autowired
    public BookImageRepository bookImageRepo;


    @Override
    public BookImage save(BookImage bookImage){
        BookImage imageBD = bookImageRepo.findBookImageByName(bookImage.getName());
        if(imageBD!=null){
           bookImageRepo.delete(imageBD);
           return bookImageRepo.save(bookImage);
        }else{
            return bookImageRepo.save(bookImage);
        }
    }


    @Override
    public BookImage findBookImageByName(String name) {
        return bookImageRepo.findBookImageByName(name);
    }


    @Override
    public void delete(String id) {
        BookImage image = findBookImageByName(id);
        if(image==null){
            throw new NotFoundException("Image not found");
        }else{
          bookImageRepo.delete(image);  
        }
        
    }
    
}
