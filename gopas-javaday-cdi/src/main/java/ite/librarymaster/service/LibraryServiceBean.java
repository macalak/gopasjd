package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.dao.JpaRepository;
import ite.librarymaster.interceptor.Tracer;
import ite.librarymaster.model.Book;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;

/**
 * Stateless implementation of the LibraryService interface.
 * It acts as service facade.
 * 
 * @author macalak@itexperts.sk
 *
 */

@Service
@Tracer
public class LibraryServiceBean implements LibraryService{
	
	@Inject
	private Logger logger;
	
	@Inject 
	//@JpaRepository
	private BookRepository bookRepository;
	
	@PostConstruct
	public void potConstruct(){
		logger.info("In LibraryServiceBean::potConstruct() <-");
	}

	/**
	 * Returns all Books from Library. It uses injected BookRepository EJB to delegate query to 
	 * Book repository implementation. 
	 * The business methods of Stateless session beans are thread-safe.
	 */
	@Override
	public List<Book> getAllBooks() {
		logger.info("In LibraryServiceBean::getAllBooks() <-");
		return bookRepository.findAll();
	}

	@Override
	public Book getByIsbn(String isbn) {
		logger.info("In LibraryServiceBean::getByIsbn() <-");
		return bookRepository.findByIsbn(isbn);
	}
}
