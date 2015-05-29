package ite.librarymaster.web;

import ite.librarymaster.model.Book;
import ite.librarymaster.model.MediumAvailability;
import ite.librarymaster.service.LibraryAdminService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;

@Named("addBookController")
@RequestScoped
public class AddBookController{
	@Inject
	private Logger logger;
	
	// Built-in bean
	//@Inject
    //HttpServletRequest httpServletRequest;
	
	private Book book;
	
	@Inject
	LibraryAdminService libraryAdminService;
	
	@PostConstruct
	public void init(){
		logger.info("init(): Initializing Book...");
		book = new Book();
		book.setAvailability(MediumAvailability.Available);
	}
	
	public String add(){
		logger.info("add(): Going to save Book {}.",book);
		//logger.info("REquest URI={}",httpServletRequest.getRequestURI());
		libraryAdminService.addBook(book);
		return "books";
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
