package com.stresslesslibrary.bookservice.entities;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.*;

@Entity
@JsonInclude(Include.NON_NULL)
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
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
	
	public Publisher() {
		// TODO Auto-generated constructor stub
	}
	public Publisher(String name) {
		super();
		this.name = name;
	}
	
	
	
}
