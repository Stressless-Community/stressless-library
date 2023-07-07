package com.stresslesslibrary.bookservice.servicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.stresslesslibrary.bookservice.entities.Language;
import com.stresslesslibrary.bookservice.entities.PrintKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.webjars.NotFoundException;

import com.stresslesslibrary.bookservice.dtos.BookDTO;
import com.stresslesslibrary.bookservice.entities.Author;
import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.entities.BookImage;
import com.stresslesslibrary.bookservice.entities.BookIndex;
import com.stresslesslibrary.bookservice.repositories.AuthorRepository;
import com.stresslesslibrary.bookservice.repositories.BookIndexRepository;
import com.stresslesslibrary.bookservice.repositories.BookRepository;
import com.stresslesslibrary.bookservice.services.AuthorService;
import com.stresslesslibrary.bookservice.services.BookImageService;
import com.stresslesslibrary.bookservice.services.BookIndexService;
import com.stresslesslibrary.bookservice.services.BookService;
import com.stresslesslibrary.bookservice.services.BranchService;
import com.stresslesslibrary.bookservice.services.PublisherService;


@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookIndexService indexService ;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookImageService bookImageService;
	
	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public Book getOne(String isbn) {
		// TODO Exception handler
		return bookRepository.findById(isbn).orElse(null);
	}

	@Override
	public List<Book> searchPatern(String keyword) {
		List<Book> matches= bookRepository.search(keyword);
				
		
			return matches;
		
	}
	
	

	@Override
	public Book saveBook(BookDTO book) {
	Book b= new Book();
	try {
		b.setIsbn(book.getIsbn());
		b.setTitle(book.getTitle());
		b.setSubtitle(book.getSubtitle());
		b.setDescription(book.getDescription());
		b.setLanguage(Language.valueOf(book.getLanguage()));
		b.setPageCount(book.getPageCount());
		b.setPublishedDate(book.getPublishedDate());
		b.setKind(PrintKind.valueOf(book.getKind()));
		b.setPdfAvailble(book.getPdfAvailble());
		b.setEpubAvailble(book.getEpubAvailble());
		b.setIsReference(book.getIsReference());
		b.setPublisher(publisherService.getOne(book.getPublisher()));
		b.setBranch(branchService.getOne(book.getBranchId()));
		b.setRecordedDate(new Date());
		List<Author> authors = new ArrayList<Author>();
		List<BookIndex> indexes = new ArrayList<BookIndex>();
		for (int author : book.getAuthors()) {
		 authors.add(authorService.getOne(author));	
		}
		if(book.getIndexes()!=null){
			for(int index : book.getIndexes()){
				if(indexService.getOne(index)!= null){
					indexes.add(indexService.getOne(index));
				}
			}
			b.setIndexes(indexes);
		}
		b.setAuthors(authors);
		return bookRepository.save(b);
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
	
		
	
	}

	@Override
	public Boolean setAuthor(int authorId, String isbn) {
		try {
			Book b = getOne(isbn);
			b.addAuthor(authorService.getOne(authorId));
			bookRepository.save(b);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public Book updateBook(BookDTO book) {
		try {
			Book b = new Book();
			b.setIsbn(book.getIsbn());
			b.setTitle(book.getTitle());
			b.setSubtitle(book.getSubtitle());
			b.setDescription(book.getDescription());
			b.setLanguage(Language.valueOf(book.getLanguage()));
			b.setPageCount(book.getPageCount());
			b.setPublishedDate(book.getPublishedDate());
			b.setKind(PrintKind.valueOf(book.getKind()));
			b.setPdfAvailble(book.getPdfAvailble());
			b.setEpubAvailble(book.getEpubAvailble());
			b.setIsReference(book.getIsReference());
			b.setPublisher(publisherService.getOne(book.getPublisher()));
			b.setBranch(branchService.getOne(book.getBranchId()));
			b.setRecordedDate(new Date());
			List<Author> authors = new ArrayList<Author>();
			for (int author : book.getAuthors()) {
			authors.add(authorService.getOne(author));	
			}
			for(int indexe : book.getIndexes()){
				
			}
			b.setAuthors(authors);
			return bookRepository.save(b);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public Book update(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> recentBooks() {
		return bookRepository.findTop10ByOrderByRecordedDateDesc();
	}

	@Override
	public void deleteBook(String id) {
		Book b = getOne(id);
		BookImage cover = bookImageService.findBookImageByName(id);
		if(b!=null && cover==null){
			bookRepository.delete(b);
		}else if(cover!=null && b!= null){
			bookImageService.delete(id);
			bookRepository.delete(b);
		}else if(b == null){
			throw new NotFoundException("Book not found");
		}

		
	}


}
