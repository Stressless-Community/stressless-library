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

import com.stresslesslibrary.bookservice.dtos.BookDTO;
import com.stresslesslibrary.bookservice.entities.Author;
import com.stresslesslibrary.bookservice.entities.Book;
import com.stresslesslibrary.bookservice.repositories.AuthorRepository;
import com.stresslesslibrary.bookservice.repositories.BookIndexRepository;
import com.stresslesslibrary.bookservice.repositories.BookRepository;
import com.stresslesslibrary.bookservice.services.AuthorService;
import com.stresslesslibrary.bookservice.services.BookService;
import com.stresslesslibrary.bookservice.services.BranchService;
import com.stresslesslibrary.bookservice.services.PublisherService;


@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookIndexRepository bookIndexRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private AuthorService authorService;
	
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
		List<String> indexes= new ArrayList<>();
		List<Book> matches= new ArrayList<>();
			try (Scanner scanner = new Scanner(keyword)) {
				while (scanner.hasNext()) {
					indexes.add(scanner.next());
				}
			}
			if(indexes.isEmpty()||keyword.isBlank()||keyword.isEmpty()) {
					matches.addAll(bookRepository.findAll());
				}else {
					matches.addAll(bookRepository.search(indexes.get(0).toLowerCase()));
					if(matches.isEmpty()) {
						authorRepository.search(keyword.toLowerCase()).forEach(au->{
							matches.addAll(au.getBooks());
						
						});;
						if(matches.isEmpty()) {
							bookIndexRepository.search(keyword.toLowerCase());
						}
					}
				}
				
		
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
		LocalDate localDate = LocalDate.now();
		b.setRecordedDate(new Date(localDate.getYear(),localDate.getMonthValue(), localDate.getDayOfMonth()));
		List<Author> authors = new ArrayList<Author>();
		for (int author : book.getAuthors()) {
		 authors.add(authorService.getOne(author));	
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
			Book b=getOne(book.getIsbn());
			b.setIsbn(book.getIsbn());
			b.setTitle(book.getTitle());
			b.setSubtitle(book.getSubtitle());
			b.setDescription(book.getDescription());
//			b.setLanguage(book.getLanguage());
			b.setPageCount(book.getPageCount());
			b.setPublishedDate(book.getPublishedDate());
//			b.setKinds(book.getKinds());
			b.setPdfAvailble(book.getPdfAvailble());
			b.setEpubAvailble(book.getEpubAvailble());
			b.setIsReference(book.getIsReference());
			b.setPublisher(publisherService.getOne(book.getPublisher()));
			b.setBranch(branchService.getOne(book.getBranchId()));
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
	public List<Book> popularBooks() {
		return bookRepository.findTop10ByOrderByRecordedDateDesc();
	}


}
