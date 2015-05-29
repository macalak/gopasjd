package ite.librarymaster.service;

import ite.librarymaster.model.Book;

import java.util.List;

/**
 * This interface defines the Library functions.
 * It acts as main interface to the Library Master application.
 * The Library Service is accessible locally and remotely.
 * 
 * @author macalak@itexperts.sk
 *
 */
public interface LibraryService {
	
	/**
	 * Retrieves all Books in the Library.
	 * @return All books in the Library
	 */
	List<Book> getAllBooks();
	
	/**
	 * Retrieves a Book identified by its ISBN number.
	 * 
	 * @param isbn - ISBN of a Book
	 * @return Book
	 */
	Book getByIsbn(String isbn);
	
}
