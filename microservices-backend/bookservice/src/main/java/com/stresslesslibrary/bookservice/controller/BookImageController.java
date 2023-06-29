package com.stresslesslibrary.bookservice.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Optional;

import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stresslesslibrary.bookservice.entities.BookImage;
import com.stresslesslibrary.bookservice.repositories.BookImageRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class BookImageController {
	
	@Autowired
	private BookImageRepository bookImage;

	@Autowired
	private BookService bookService;
	
	@PostMapping
	public ResponseEntity<String> uploadAttachment(@RequestParam("file") MultipartFile file,String isbn){
		
		try {
			Book book=bookService.getOne(isbn);
			BookImage image= new BookImage(isbn, file.getContentType(), file.getBytes());
			image.setBook(book);
			bookImage.save(image);
			book.setBookImage(image);
			bookService.update(book);


			return ResponseEntity.ok().body("Success upload");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	

	    @GetMapping(path = {"/{name}"})
	    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws Exception {
	        	BookImage dbImage=new BookImage();
				try {
					dbImage = bookImage.findBookImageByName(name);
					return ResponseEntity
							.ok()
							.contentType(MediaType.valueOf(dbImage.getType()))
							.body((dbImage.getImage()));
				} catch (Exception e) {
					return ResponseEntity
							.notFound()
							.build();
				}


	    }
}
