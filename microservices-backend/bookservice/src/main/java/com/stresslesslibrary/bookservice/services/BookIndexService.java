package com.stresslesslibrary.bookservice.services;

import java.util.List;

import com.stresslesslibrary.bookservice.entities.BookIndex;

public interface BookIndexService {
    
    List<BookIndex> findAll();

    BookIndex getOne(int id);

    List<BookIndex> search(String keyword);

    BookIndex save(BookIndex bookIndex);
}
