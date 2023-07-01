package com.stresslesslibrary.bookservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor 
@Data 
@NoArgsConstructor 
@ToString
public class BookRecommendation{
    private String isbn;
    private String coverUrl;
}