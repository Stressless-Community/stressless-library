package com.stresslesslibrary.bookservice.dtos;

import com.stresslesslibrary.bookservice.entities.Language;
import com.stresslesslibrary.bookservice.entities.PrintKind;

public class BookDTO {

	private String isbn;
	private String title;
	private String subtitle;
	private String description;
	private Language language;
	private int pageCount;
	private String publishedDate;
	private PrintKind kinds;
	private Boolean pdfAvailble;
	private Boolean epubAvailble;
	private Boolean isReference;
	private int publisherId;
	
	private String branchId;

	public String getIsbn() {
		return isbn;
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

	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public BookDTO(String isbn, String title, String subtitle, String description, Language language, int pageCount,
			String publishedDate, PrintKind kinds, Boolean pdfAvailble, Boolean epubAvailble, Boolean isReference,
			int publisherId, String branchId) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.language = language;
		this.pageCount = pageCount;
		this.publishedDate = publishedDate;
		this.kinds = kinds;
		this.pdfAvailble = pdfAvailble;
		this.epubAvailble = epubAvailble;
		this.isReference = isReference;
		this.publisherId = publisherId;
		this.branchId = branchId;
	}

	public BookDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
