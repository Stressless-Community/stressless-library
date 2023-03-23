package com.stresslesslibrary.bookservice.entities;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.*;

@Entity
@Table(name = "bookindexes")
@JsonInclude(Include.NON_NULL)
public class BookIndex {
	@Id
	private int id;
	private String name;
	
	@ManyToMany(mappedBy = "indexes")
	private List<Book> books;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public BookIndex() {
		super();
	}
	
	
	
}
