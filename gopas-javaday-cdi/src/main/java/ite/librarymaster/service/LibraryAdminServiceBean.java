package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.dao.JpaRepository;
import ite.librarymaster.model.Book;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

/**
 * Stateless LibraryAdminService implementation.
 * 
 * @author macalak@itexperts.sk
 *
 */
@Service
public class LibraryAdminServiceBean implements LibraryAdminService{
	@Inject
	private Logger logger;
	
	@Inject 
	//@JpaRepository
	private BookRepository bookRepository;

	@Override
	public void addBook(Book book) {
		logger.info("<-");
		bookRepository.saveBook(book);
	}

	@Override
	public void addBooks(List<Book> books) {
		logger.info("<-");
		for(Book book : books){
			bookRepository.saveBook(book);
		}
	}
}
