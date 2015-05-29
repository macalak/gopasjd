package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.interceptor.MonitorInterceptor;
import ite.librarymaster.interceptor.TraceInterceptor;
import ite.librarymaster.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stateless implementation of the LibraryService interface.
 * It acts as service facade.
 * 
 * @author macalak@itexperts.sk
 *
 */

// http://localhost:8080/LibraryService/LibraryServiceBean?Tester
@Stateless(mappedName="ejb/LibraryServiceBean")
@Interceptors({TraceInterceptor.class , MonitorInterceptor.class})
public class LibraryServiceBean implements LibraryService, LibraryServiceLocal{
	final Logger logger = LoggerFactory.getLogger(LibraryServiceBean.class);
	private final static long SEARCH_PROC_DURATION=15000;
	
	@EJB
	private BookRepository bookRepository;
	@Resource 
	private SessionContext sessionContext ;
	
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

	@Asynchronous
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	@Override
	public Future<List<Book>> searchBooks() {
		logger.info("In LibraryServiceBean::searchBooks() <-");
		logger.info("searchBooks() started in {} thread.",Thread.currentThread().getName());
		List<Book> result = new ArrayList<Book>();
		List<Book> foundBooks=bookRepository.findAll();
		for(Book book:foundBooks){
			try {
				Thread.sleep(SEARCH_PROC_DURATION);
				result.add(book);
				logger.info("searchBooks(): Has book found: {}",book);
			} catch (InterruptedException e) {
				logger.warn("Sleep interrupted!");
			}
		}
		
		return new AsyncResult<List<Book>>(result);
	}

	@Override
	public Book getByIsbn(String isbn) {
		logger.info("In LibraryServiceBean::getByIsbn() <-");
		return bookRepository.findByIsbn(isbn);
	}
}
