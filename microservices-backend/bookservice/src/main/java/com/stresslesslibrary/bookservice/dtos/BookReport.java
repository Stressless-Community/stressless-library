package com.stresslesslibrary.bookservice.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class BookReport {
    
    private int totalBooks;
    private List<BookCaseCount> bookcasesCounts;

    public BookReport(){
        this.bookcasesCounts = new ArrayList<BookCaseCount>();
    }

    public void addBookCaseCount(BookCaseCount bookCaseCount){
        bookcasesCounts.add(bookCaseCount);
    }

}
