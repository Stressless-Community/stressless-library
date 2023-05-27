package com.stresslesslibrary.bookservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class BookImage {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name",unique = true)
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "image", unique = false, nullable = false, length = 100000)
	private byte[] image;
	
	@OneToOne
	private Book book; 
	
	public BookImage(String name, String contentType, byte[] bytes) {
		this.name=name;
		this.type=contentType;
		this.image=bytes;
	}

	public BookImage(String name, String contentType, byte[] bytes, Book book) {
		this.name=name;
		this.type=contentType;
		this.image=bytes;
		this.book=book;
	}

}
