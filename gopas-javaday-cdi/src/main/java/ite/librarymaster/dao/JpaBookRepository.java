package ite.librarymaster.dao;

import ite.librarymaster.model.Book;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JPA implementation of the BookRepository.
 * 
 * @author macalak@itexperts.sk
 *
 */
@JpaRepository @Alternative
@Transactional(TxType.MANDATORY)
public class JpaBookRepository implements BookRepository,Serializable {
	@Inject
	private Logger logger;
	
	@PersistenceContext(name="libraryPU")
	EntityManager entityManager; 

	@Override
	public List<Book> findAll() {
		logger.info("Searching for all books in the library...");
		List<Book> result=entityManager.createNamedQuery("book.findAll",Book.class).getResultList();
		return result;
	}

	@Override
	@Transactional(TxType.SUPPORTS)
	public Book findByIsbn(String isbn) {
		logger.info("Searching book by its isbn {} ...",isbn);
		TypedQuery<Book> namedQuery = entityManager.createNamedQuery("book.findByIsbn",Book.class);
		namedQuery.setParameter("isbn", isbn);
		return namedQuery.getSingleResult();
	}

    @Override
    @Transactional(TxType.SUPPORTS)
    public Book findById(Long id) {
    	logger.info("Searching book by its ID {} ...",id);
        return entityManager.find(Book.class, id);
    }

	@Override
	public void saveBook(Book book) {
		logger.info("Saving book {} ...",book);
		if(book.getId() == null){
			entityManager.persist(book);
		} else {
			entityManager.merge(book);
		}
	}
}
