package com.stresslesslibrary.bookservice.services;

import com.stresslesslibrary.bookservice.entities.BookImage;

public interface BookImageService {
    
    BookImage save(BookImage bookImage);

    BookImage findBookImageByName(String name);

    void delete(String id);
}
