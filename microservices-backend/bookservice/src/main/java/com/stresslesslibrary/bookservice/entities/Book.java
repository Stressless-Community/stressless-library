package com.stresslesslibrary.bookservice.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


import lombok.ToString;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "books")
@ToString
@JsonInclude(Include.NON_NULL)
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "isbn") //To avoid infinite loop in mapping
public class Book implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String isbn;
	private String title;
	private String subtitle;
	
	@Column(columnDefinition="TEXT")
	private String description;
	private Language language;
	private int pageCount;
	private String publishedDate;
	private PrintKind kinds;
	private Boolean pdfAvailble;
	private Boolean epubAvailble;
	private Boolean isReference;
	
	@Temporal(TemporalType.DATE)
	private Date recordedDate;

	@OneToOne(mappedBy = "book")
	private BookImage bookImage;
	
	//mapping
	@ManyToOne
	private Publisher publisher;
	@ManyToOne
	private Branch branch;
	
	@ManyToMany
	@JoinTable(
	  name = "book_indexes", 
	  joinColumns = @JoinColumn(name = "book_id"), 
	  inverseJoinColumns = @JoinColumn(name = "index_id"))
	private List<BookIndex> indexes;
	
	@ManyToMany(targetEntity = Author.class)
	@JoinTable(
			name = "book_authors", 
			  joinColumns = @JoinColumn(name = "book_id"), 
			  inverseJoinColumns = @JoinColumn(name = "author_id"))
	private List<Author> authors;
	
	@OneToMany(mappedBy = "book")
	private List<BookItem> bookitems;

	public String getIsbn() {
		return isbn;
	}

	public void setBookImage(BookImage bookImage) {
		this.bookImage = bookImage;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public PrintKind getKinds() {
		return kinds;
	}

	public void setKinds(PrintKind kinds) {
		this.kinds = kinds;
	}

	public Boolean getPdfAvailble() {
		return pdfAvailble;
	}

	public void setPdfAvailble(Boolean pdfAvailble) {
		this.pdfAvailble = pdfAvailble;
	}

	public Boolean getEpubAvailble() {
		return epubAvailble;
	}

	public void setEpubAvailble(Boolean epubAvailble) {
		this.epubAvailble = epubAvailble;
	}

	public Boolean getIsReference() {
		return isReference;
	}

	public void setIsReference(Boolean isReference) {
		this.isReference = isReference;
	}

	public String getPublisher() {
		return publisher.getName();
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String getBranch() {
		return branch.getId();
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	@JsonInclude(Include.NON_EMPTY)
	public List<BookIndex> getIndexes() {
		return indexes;
	}

	public void setIndexes(List<BookIndex> indexes) {
		this.indexes = indexes;
	}
	
	@JsonInclude(Include.NON_EMPTY)
	public String getAuthors() {
		String listauthors="";
		for (int i=0;i<this.authors.size();i++) {
			listauthors=listauthors+authors.get(i).getName()+",";
			}
		return listauthors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	@JsonInclude(Include.NON_EMPTY)
	public List<BookItem> getBookitems() {
		return bookitems;
	}

	public void setBookitems(List<BookItem> bookitems) {
		this.bookitems = bookitems;
	}
	
	public String getLargeCoverUrl() {
		if (bookImage == null){
			UriComponents uri =ServletUriComponentsBuilder.fromCurrentContextPath()
					//.port(${server.port})
					.path("image/0000000000000")
					.build();
			String urlString = uri.toString();
			return urlString;
		}else{
			UriComponents uri =ServletUriComponentsBuilder.fromCurrentContextPath()
					//.port(${server.port})
					.path("image/"+isbn)
					.build();
			String urlString = uri.toString();
			return urlString;
		}





	}
	
	public Long getAvailableItems() {
		//Using Java Steam to return the count of availableitems
		try {
			return bookitems.stream().filter(s->s.getItemStatus().equals(ItemStatus.AVAILABLE)).count();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public String getPdfurl() {
		if(this.pdfAvailble) {
			UriComponents uri =ServletUriComponentsBuilder.fromCurrentContextPath()
					.port(8082)
					.path("/attachments/pdf/"+isbn+"pdf")
					.build();
	              

			String urlString = uri.toString();
			return urlString;
		}
		return null;
	}
	
	public void addAuthor(Author author) {
		authors.add(author);
	}
	
	
	 
}
