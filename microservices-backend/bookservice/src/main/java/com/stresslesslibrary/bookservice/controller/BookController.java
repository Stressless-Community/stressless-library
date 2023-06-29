package com.stresslesslibrary.bookservice.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stresslesslibrary.bookservice.dtos.BookDTO;
import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.services.BookService;
import com.stresslesslibrary.bookservice.services.BranchService;
import com.stresslesslibrary.bookservice.servicesImpl.ExcelService;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@CrossOrigin("*")
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BranchService branchservice;
	
	@GetMapping
	public ResponseEntity<List<Book>> findAll(){
		List<Book> books= bookService.findAll();
		if(books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	
	@GetMapping("/{isbn}")
	public ResponseEntity<Book> findById(@PathVariable(value = "isbn") String isbn){
		Book book = bookService.getOne(isbn);
		if (book==null){
			return ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(bookService.getOne(isbn));
		}
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Book>> search(@RequestParam("keyword") String keyword){
		return ResponseEntity.ok().body(bookService.searchPatern(keyword));
	}
	
	@GetMapping("/popular")
	public ResponseEntity<List<Book>> popular(){
		return ResponseEntity.ok().body(bookService.popularBooks());
	}

	@PostMapping
	public ResponseEntity<?> saveBook(@RequestBody BookDTO book){
		System.out.print(book.toString());
		Book bookDB = bookService.getOne(book.getIsbn());
		if (bookDB == null){
			Book b=bookService.saveBook(book);
			if(b==null){
			return ResponseEntity.badRequest().body(book);
			}
			return ResponseEntity.ok().body(b);
		}else{
			return ResponseEntity.badRequest().body(book);
		}
		
	}
	
	@PostMapping("/add-author")
	public ResponseEntity<String> setAuthor(@RequestParam int authorId, String isbn){
		
		if(bookService.setAuthor(authorId, isbn)) {
		return ResponseEntity.ok().body("Add succefully");
		}
		return ResponseEntity.badRequest().body("Cannot deal this those id");
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody BookDTO book){
		try {
			return ResponseEntity.ok().body(bookService.getOne(book.getIsbn()));
		} catch (Exception e) {
			
			Book b=bookService.updateBook(book);
			if(b!=null) {
			return ResponseEntity.ok().body(b);}
			return ResponseEntity.badRequest().body(book);
		}
		
	}
	
	 @GetMapping("/export-to-excel")
	    public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());

	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=books" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	        
	        List <Book> books = bookService.findAll();
	        ExcelService generator = new ExcelService(books,branchservice.findAll());
	        generator.generateExcelFile(response);
	 }
	 
	 
}
