package ite.librarymaster.web;

import ite.librarymaster.model.Book;
import ite.librarymaster.model.MediumAvailability;
import ite.librarymaster.service.LibraryAdminServiceLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@javax.faces.bean.ManagedBean(name="addBookController")
@javax.faces.bean.RequestScoped
public class AddBookController {
	final Logger logger = LoggerFactory.getLogger(AddBookController.class);
	
	private Book book;
	
	@EJB
	LibraryAdminServiceLocal libraryAdminService;
	
	@PostConstruct
	public void init(){
		logger.info("init(): Initializing Book...");
		book = new Book();
		book.setAvailability(MediumAvailability.Available);
	}
	
	public String add(){
		logger.info("add(): Going to save Book {}.",book);
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
