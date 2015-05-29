package ite.librarymaster.service;

import ite.librarymaster.model.Book;

import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Local;

/**
 * This interface defines the Library functions.
 * It acts as main interface to the Library Master application.
 * The Library Service is accessible locally and remotely.
 * 
 * @author macalak@itexperts.sk
 *
 */
@Local
public interface LibraryServiceLocal {
	
	/**
	 * Retrieves all Books in the Library.
	 * @return All books in the Library
	 */
	List<Book> getAllBooks();
	
	/**
	 * Starts search Library process.
	 * Method is invoked asynchronously to not block clients.
	 * The returned Future object can be used to monitor
	 * started process and to retrieve result. 
	 * @return
	 */
	Future<List<Book>> searchBooks();
	
}
