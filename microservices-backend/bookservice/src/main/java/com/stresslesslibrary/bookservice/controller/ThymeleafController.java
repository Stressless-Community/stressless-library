package com.stresslesslibrary.bookservice.controller;

import com.stresslesslibrary.bookservice.dtos.BookDTO;
import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.BookImage;
import com.stresslesslibrary.bookservice.services.BookImageService;
import com.stresslesslibrary.bookservice.services.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;


@Controller
@CrossOrigin("*")
public class ThymeleafController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookImageService bookImageRepository;

    public ThymeleafController(BookService bookService){

    }
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("catalogs", bookService.findAll());
        return "index";
    }

    @GetMapping("/book/{isbn}")
    public String getbook(@PathVariable("isbn") String isbn, Model model) {

        Book book = bookService.getOne(isbn);
        model.addAttribute("book", book);

        return "book";
    }

    @GetMapping("/addbook")
    public String addbook(Model model) {
        model.addAttribute("bookDto", new BookDTO());
        return "add-book";
    }

    @PostMapping("/addbook")
    public String addbook(@Valid BookDTO bookDTO, BindingResult result, Model model) throws IOException {

        model.addAttribute("bookDto", new BookDTO());
        if (result.hasErrors()) {

            return "add-book";

        }else{
           MultipartFile file = bookDTO.getCover();
           Book book= bookService.saveBook(bookDTO);
           BookImage image= new BookImage(bookDTO.getIsbn(), file.getContentType(), file.getBytes(), book);
           bookImageRepository.save(image);


            return "redirect:/";
        }

            }

}




