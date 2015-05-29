package ite.librarymaster.dao;

import ite.librarymaster.model.Book;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

import org.slf4j.Logger;

//@Priority(Interceptor.Priority.APPLICATION+10)
@Decorator
public class SynchronizedRepository implements BookRepository {
	@Inject 
	private Logger logger;
	private ReentrantLock lock = new ReentrantLock();
	
	@Inject
	@Delegate
	@Any
	BookRepository bookRepository;
	
	@Override
	public List<Book> findAll() {
		logger.info("Locking BookRepository ...");
		lock.lock();
		try{
			return bookRepository.findAll();
		}finally{
			logger.info("Unlocking BookRepository ...");
			lock.unlock();
		}
	}

	@Override
	public Book findByIsbn(String isbn) {
		lock.lock();
		try{
			return bookRepository.findByIsbn(isbn);
		}finally{
			lock.unlock();
		}
	}

	@Override
	public Book findById(Long id) {
		lock.lock();
		try{
			return bookRepository.findById(id);
		}finally{
			lock.unlock();
		}
	}

	@Override
	public void saveBook(Book book) {
		lock.lock();
		try{
			 bookRepository.saveBook(book);
		}finally{
			lock.unlock();
		}
		
	}

}
