package com.stresslesslibrary.bookservice.dtos;

import java.util.List;

import lombok.Data;

@Data 
public class BookReport {

    private List<BookCaseCount> bookcases;
}
