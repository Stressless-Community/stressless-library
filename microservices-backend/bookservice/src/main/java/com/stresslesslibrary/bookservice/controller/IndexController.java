package com.stresslesslibrary.bookservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stresslesslibrary.bookservice.entities.BookIndex;
import com.stresslesslibrary.bookservice.services.BookIndexService;

@RestController
@RequestMapping("/indexes")
public class IndexController {
    @Autowired
    private BookIndexService indexService;

    @GetMapping
    public ResponseEntity<List<BookIndex>> getAll(){
        return ResponseEntity.ok().body(indexService.findAll()) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookIndex> getOne(@RequestParam(value = "id") int id){
        if(indexService.getOne(id)!=null){
            return ResponseEntity.ok().body(indexService.getOne(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookIndex> saveIndex(@RequestBody BookIndex index){

        return ResponseEntity.ok().body(indexService.save(index));
    }

    
	@GetMapping("/search")
	public ResponseEntity<List<BookIndex>> search(@RequestParam(defaultValue = "") String keyword){
			
		return ResponseEntity.ok().body(indexService.search(keyword));
				
	}




}
