package com.stresslesslibrary.bookservice.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.*;

@Entity
@Table(name = "bookcategories")
@JsonInclude(Include.NON_NULL)
public class BookCategory {
	@Id
	private String id;
	
	private String name;
	
	@OneToMany(mappedBy = "category")
	private List<Branch> branches;
	
	@ManyToOne
	private BookCase bookCase;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@JsonIgnore
	public List<Book> getBooks(){
		List<Book> books = new ArrayList<Book>();
		for (Branch b : this.branches){
			books.addAll(b.getBooks());
		}
		return books;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public String getBookCase() {
		try {
			return bookCase.getId();
		} catch (Exception e) {
			return null;
		}
		
	}

	public void setBookCase(BookCase bookCase) {
		this.bookCase = bookCase;
	}

	public BookCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookCategory(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	
	
}
