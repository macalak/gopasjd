package ite.librarymaster.dao;

import ite.librarymaster.model.Book;

import java.util.List;

import javax.ejb.Local;

/**
 * Book Repository interface defines operations to
 * access and manipulate Books.
 * 
 * @author macalak@itexperts.sk
 *
 */
@Local
public interface BookRepository {
	
	/**
	 * Finds all Books in the Library.
	 * @return - All books from Library
	 */
	List<Book> findAll();

	/**
	 * Finds book by isbn number.
	 * @param isbn - book isbn number
	 * @return Book with given isbn, or null if no such book exists in the Library
	 */
	Book findByIsbn(String isbn);
	
	/**
	 * Finds book by book id.
	 * @param id - Book Id
	 * @return Book with given id, or null if no such book exists in the Library
	 */
	Book findById(Long id);
	
	/**
	 * Saves given book into Library.
	 * @param book - Book to save
	 */
	void saveBook(Book book);

}
