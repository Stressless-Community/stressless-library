package com.stresslesslibrary.bookservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @ AllArgsConstructor
public class BookCaseCount {
    
    private String id;
    private String name;
    private int BooksCount;
}
