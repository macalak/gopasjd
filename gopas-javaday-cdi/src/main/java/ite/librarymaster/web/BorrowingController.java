package ite.librarymaster.web;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.dao.JpaRepository;
import ite.librarymaster.model.Book;
import ite.librarymaster.model.MediumAvailability;
import ite.librarymaster.service.BorrowingService;
import ite.librarymaster.service.LibraryException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

/**
 * Session scoped CDI Bean. It maintains the Book borrowings
 * in a client's session.
 * 
 * @author macalak@itexperts.sk
 *
 */

@SuppressWarnings("serial")
@Named("borrowingController")
@SessionScoped
public class BorrowingController implements Serializable{
	@Inject
	private Logger logger;
	
	@Inject 
	//@JpaRepository
	private BookRepository bookRepository;
	
	@Inject
	private BorrowingService borrowingService;
	
	// Internal state
	private List<Book> bookBorrowings;
	private String selectedBookIsbn;
	
	/**
	 * Initializes list of borrowings.
	 */
	@PostConstruct
	public void initialize(){
		logger.info("In BorrowingBean::initialize() <-");
		bookBorrowings = new ArrayList<Book>();
		selectedBookIsbn=null;
	}

	@PreDestroy
	public void destroy(){
		logger.info("In BorrowingBean::destroy() <-");
		bookBorrowings.clear();
		bookBorrowings = null;
	}

	/**
	 * Check-outs the borrowing.
	 * The borrowed library items will be marked as borrowed and
	 * new user borrowing will be created.
	 */
	public String checkOutBorrowing(){
		logger.info("In BorrowingBean::checkOutBorrowing() <-");
		if(!bookBorrowings.isEmpty()){
			// Transaction is started here
			try {
				borrowingService.borrowBooks(bookBorrowings);
				bookBorrowings.clear();
			} catch (LibraryException e) {
				logger.info("checkOutBorrowing(): Exception caught {}",e.getMessage());
			}
			
			return "books";
		}
		return null;
		
	}
	
	/**
	 * Cancels the borrowing.
	 * The persistent storage will not be altered.
	 */
	public void cancelBorrowing(){
		logger.info("In BorrowingBean::cancelBorrowing() <-");
		bookBorrowings.clear();
	}

    /**
     * Adds given book to current list of book borrowings.
     */
	public void borrowBook() {
		logger.info("In BorrowingBean::borrowBook() <-");
		Book book = bookRepository.findByIsbn(selectedBookIsbn);
		if(book != null && book.getAvailability().equals(MediumAvailability.Available)){
			if(!bookBorrowings.contains(book)){
				bookBorrowings.add(book);
			}
			selectedBookIsbn = null;
		}else{
			logger.warn("Book with ISBN {} not found, or it is not available!",selectedBookIsbn);
		}
	}

	/**
	 * Returns current list of book borrowings.
	 */
	public List<Book> getBookBorrowings() {
		return bookBorrowings;
	}

	public String getSelectedBookIsbn() {
		return selectedBookIsbn;
	}

	public void setSelectedBookIsbn(String selectedBookIsbn) {
		this.selectedBookIsbn = selectedBookIsbn;
	}
}
