package com.stresslesslibrary.bookservice.controller;

import com.stresslesslibrary.bookservice.dtos.BookDTO;
import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.BookImage;
import com.stresslesslibrary.bookservice.repositories.BookImageRepository;
import com.stresslesslibrary.bookservice.services.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.hibernate.dialect.SybaseASE157Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
@CrossOrigin("*")
public class ThymeleafController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookImageRepository bookImageRepository;
    @Autowired
    private HttpServletRequest request;

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
        return "index";
    }

    @PostMapping("/addbook")
    public String addbook(@Valid BookDTO bookDTO, BindingResult result, Model model) throws IOException {

        model.addAttribute("bookDto", new BookDTO());
        if (result.hasErrors()) {

            return "index";

        }else{
           MultipartFile file = bookDTO.getCover();
           BookImage image= new BookImage(bookDTO.getIsbn(), file.getContentType(), file.getBytes());
           System.out.print(file.getOriginalFilename());
           BookImage imagedb = bookImageRepository.save(image);
           bookService.saveBook(bookDTO);
           imagedb.setBook(bookService.saveBook(bookDTO));
           bookImageRepository.save(imagedb);


            return "home";
        }

            }

}




