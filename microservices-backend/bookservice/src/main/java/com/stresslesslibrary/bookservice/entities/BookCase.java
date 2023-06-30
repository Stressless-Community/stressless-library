package com.stresslesslibrary.bookservice.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.*;

@Entity
@Table(name = "bookcases")
@JsonInclude(Include.NON_NULL)
public class BookCase {
	@Id
	private String id;
	private String name;
	
	@OneToMany(mappedBy = "bookCase")
	private List<BookCategory> categories;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public List<BookCategory> getCategories() {
		return categories;
	}

	@JsonIgnore
	public List<Book> getBooks(){
		List<Book> books = new ArrayList<>();
		for(BookCategory b : categories){
			books.addAll(b.getBooks());
		}
		return books;
	}
	public void setCategories(List<BookCategory> categories) {
		this.categories = categories;
	}

	public BookCase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookCase(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	
}
