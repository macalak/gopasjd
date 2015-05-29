package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.dao.JpaRepository;
import ite.librarymaster.model.Book;
import ite.librarymaster.model.Borrowing;
import ite.librarymaster.model.MediumAvailability;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;

/**
 * 
 * Implementation of the Borrowing Service.
 *  
 * @author macalak@itexperts.sk
 *
 */
@SuppressWarnings("serial")
@Service
public class BorrowingServiceBean implements BorrowingService, Serializable {
	@Inject
	private Logger logger;

	@PersistenceContext(name="libraryPU")
	private EntityManager entityManager; 
	
	@Inject 
	//@JpaRepository
	private BookRepository bookRepository;
	
	@Override
	public void borrowBooks(List<Book> books) throws LibraryException{
		logger.info("In BorrowingServiceBean::borrowBooks() <-");
		
		Borrowing borrowing = new Borrowing();
		borrowing.setBooks(books);
		borrowing.setBorrowed(new Date());
		
		logger.info("Persistting borrwing ...");
		entityManager.persist(borrowing);
		
		logger.info("Marking books as Borrowed ...");
		for(Book book: books){
			book.setAvailability(MediumAvailability.Borrowed);
			// Transaction propagation
			bookRepository.saveBook(book);
		}
	}
	
}
