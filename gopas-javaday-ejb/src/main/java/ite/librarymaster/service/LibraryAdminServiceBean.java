package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.model.Book;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stateless LibraryAdminService implementation.
 * 
 * @author macalak@itexperts.sk
 *
 */
@Stateless
public class LibraryAdminServiceBean implements LibraryAdminServiceLocal{
	final Logger logger = LoggerFactory.getLogger(LibraryAdminServiceBean.class);
	
	@EJB
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
