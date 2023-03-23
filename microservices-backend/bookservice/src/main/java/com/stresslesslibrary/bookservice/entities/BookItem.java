package com.stresslesslibrary.bookservice.entities;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.*;

@Entity
@Table(name = "bookitems")
@JsonInclude(Include.NON_NULL)
public class BookItem {
	@Id
	private Long identifier;
	
	
	private Boolean isOnPlaceReadble;
	private ItemStatus itemStatus;
	
	@ManyToOne
	private Book book;

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public Boolean getIsOnPlaceReadble() {
		return isOnPlaceReadble;
	}

	public void setIsOnPlaceReadble(Boolean isOnPlaceReadble) {
		this.isOnPlaceReadble = isOnPlaceReadble;
	}

	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getBookIsbn() {
		return book.getIsbn();
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookItem() {
		// TODO Auto-generated constructor stub
	}

	
	
}
