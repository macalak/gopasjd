package ite.librarymaster.service;

import ite.librarymaster.dao.BookRepository;
import ite.librarymaster.model.Book;
import ite.librarymaster.model.Borrowing;
import ite.librarymaster.model.MediumAvailability;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Implementation of the Borrowing Service.
 *  
 * @author macalak@itexperts.sk
 *
 */
@Stateless
@Local(BorrowingServiceLocal.class)
// CONTAINER managed transactions (CMT) are default 
// You can turn it off by TransactionManagementType.BEAN
// then you are responsible for programmatically control
// transaction boundaries 
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BorrowingServiceBean  implements BorrowingServiceLocal {
	final Logger logger = LoggerFactory.getLogger(BorrowingServiceBean.class);

	@Resource
	private SessionContext context;
	
	@PersistenceContext(name="libraryPU")
	private EntityManager entityManager; 
	
	@EJB
	private BookRepository bookRepository;
	
	@Override
	// Default Transaction propagation is REQUIRED
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void borrowBooks(List<Book> books) throws LibraryException{
		logger.info("In LibraryServiceBean::borrowBooks() <-");
		
		// BMT should use JTA User transaction
		// UserTransaction userTransaction = context.getUserTransaction();
		// userTransaction.begin();
		
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
		
		logger.info("Senfing Alarm ...");
		
		// You can avoid Transaction to be committed
		// context.setRollbackOnly();
		// logger.warn("borrowBooks(): Current Tx marked as Rollback Only!");
		
		// Application exception does not cause current Transaction to rollback
		// unless it is not market to do so by @ApplicationException annotation!
		// Some issue
		// throw new LibraryException("Problem during borrowing process!");
		
		//userTransaction.commit();
	}
	
}
