package com.stresslesslibrary.bookservice.entities;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.*;


@Entity
@Table(name = "branches")
@JsonInclude(Include.NON_NULL)
public class Branch {
	@Id
	private String id;
	private String name;
	
	@ManyToOne
	private BookCategory category;
	
	public Branch() {
		// TODO Auto-generated constructor stub
	}

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

	public String getCategory() {
		try {
			return category.getId();
		} catch (Exception e) {
			return null;
		}
		
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public Branch(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
	
}
