package com.stresslesslibrary.bookservice.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;



@Entity
@Table(name = "authors")
@JsonInclude(Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id") //To avoid infinite loop in mapping
public class Author implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name="description",columnDefinition="LONGTEXT")
	private String description;
	
	
	@JsonIgnore //This is to avoid infinite loop
	@ManyToMany(mappedBy = "authors")
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Author(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
