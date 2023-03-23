package com.stresslesslibrary.bookservice.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Optional;

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
	
	@PostMapping
	public ResponseEntity<String> uploadAttachment(@RequestParam("file") MultipartFile file,String name){
		
		try {
			BookImage image= new BookImage(name, file.getContentType(), file.getBytes());
			bookImage.save(image);
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
					dbImage = bookImage.findByName(name);
				} catch (Exception e) {
					dbImage = bookImage.findByName("noImage");
				}

	        return ResponseEntity
	                .ok()
	                .contentType(MediaType.valueOf(dbImage.getType()))
	                .body((dbImage.getImage()));
	    }
}
