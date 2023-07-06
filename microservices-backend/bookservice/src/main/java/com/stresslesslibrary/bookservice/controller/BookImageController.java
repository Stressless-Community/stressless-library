package com.stresslesslibrary.bookservice.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.services.BookImageService;
import com.stresslesslibrary.bookservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/Images/covers";
	
	@Autowired
	private BookImageService bookImage;

	@Autowired
	private BookService bookService;
	
	@PostMapping
	public ResponseEntity<String> uploadAttachment(@RequestParam("file") MultipartFile file,String isbn){
		StringBuilder fileNames = new StringBuilder();
		System.out.println(file.getContentType());
		Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
		fileNames.append(file.getOriginalFilename());
		
		try {
			Book book=bookService.getOne(isbn);
			BookImage image= new BookImage(isbn, file.getContentType(), file.getBytes());
			if (book==null){
				return ResponseEntity.badRequest().build();
			}else{
				image.setBook(book);
				BookImage response = bookImage.save(image);
				if(response==null){
					System.out.println("Image response is null");
					bookService.deleteBook(isbn);
					return ResponseEntity.badRequest().build();
				}else{
					Files.write(fileNameAndPath, file.getBytes());
					return ResponseEntity.ok().body("Success upload");
				}
				
			}
			
		} catch (Exception e) {

			bookService.deleteBook(isbn);
			return ResponseEntity.badRequest().build();
		}
	}
	

	    @GetMapping(path = {"/{isbn}"})
	    public ResponseEntity<?> getImage(@PathVariable("isbn") String isbn) throws Exception {
	        	BookImage dbImage=new BookImage();
				try {
					dbImage = bookImage.findBookImageByName(isbn);
					InputStream in =getClass().getResourceAsStream(UPLOAD_DIRECTORY+dbImage.getName());
					return ResponseEntity
							.ok()
							.contentType(MediaType.valueOf(dbImage.getType()))
							// .body(new InputStreamResource(in));
							.body(dbImage.getImage());
				} catch (Exception e) {
					return ResponseEntity
							.notFound()
							.build();
				}


	    }

		@PostMapping("/upload") 
		public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, String isbn) throws IOException {
			StringBuilder fileNames = new StringBuilder();
			Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename());
			BookImage imageDB = new BookImage(file.getOriginalFilename(), file.getContentType());
			Book book = bookService.getOne(isbn);
			if(book!=null){
				// imageDB.setBook(book);
				// bookImage.save(imageDB);
				Files.write(fileNameAndPath, file.getBytes());
				return ResponseEntity.ok().body("Upload Success");
			}else{
				System.out.println("Book not found");
				return ResponseEntity.badRequest().body("We do not find a book with isbn: "+isbn);
			}
			
    	}
}
