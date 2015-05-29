package ite.librarymaster.service;

import ite.librarymaster.model.Book;

import java.util.List;

import javax.ejb.Local;

/**
 * Library Administration service interface definition.
 * 
 * @author macalak@itexperts.sk
 *
 */

public interface LibraryAdminService {
	
	void addBook(Book book);
	void addBooks(List<Book> books);

}
