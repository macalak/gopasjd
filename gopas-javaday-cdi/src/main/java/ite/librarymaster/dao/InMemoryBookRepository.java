package ite.librarymaster.dao;

import ite.librarymaster.model.Book;
import ite.librarymaster.model.BookGenre;
import ite.librarymaster.model.MediumAvailability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
//import javax.enterprise.event.Event;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Vetoed;
import javax.inject.Inject;

import org.slf4j.Logger;

/**
 * In memory implementation of the BookRepository.
 * It keeps books in memory.
 * The default CONTAINER concurrency management is used.
 * The @Lock annotation can override business methods access type and
 * can be used on class, or method. 
 * 
 * @author macalak@itexperts.sk
 *
 */
//@Vetoed
@Alternative
@ApplicationScoped
public class InMemoryBookRepository implements BookRepository {
	@Inject
	private Logger logger;
	//final Logger logger = LoggerFactory.getLogger(InMemoryBookRepository.class);
	private Map<String,Book> books = Collections.synchronizedMap(new TreeMap<String,Book>());
	
	@Inject @Added Event<Book> bookAddedEvent;
	
	@PostConstruct
	public void initialize(){
		logger.info("In InMemoryBookRepository::initialize() <-");
		books.put("9780006479888", new Book(1L,"LM-000001","A Game of Thrones","HarperCollins Publishers","George R. R. Martin","9780006479888",BookGenre.Fantasy, MediumAvailability.Available));
		books.put("9780345538376", new Book(2L,"LM-000002","The Hobbit","Random House Inc","J.R.R. Tolkien","9780345538376",BookGenre.Fantasy, MediumAvailability.Available));
		books.put("9781849162883", new Book(3L,"LM-000003","The Girl with the Dragon Tattoo","Quercus Publishing Plc","Stieg Larsson","9781849162883",BookGenre.Crime, MediumAvailability.Available));
		books.put("9780553382563", new Book(4L,"LM-000004","I, Robot","Random House Inc","Isaac Asimov","9780553382563",BookGenre.Scifi, MediumAvailability.Available));
	}
	
	@PreDestroy
	public void destroy(){
		logger.info("In InMemoryBookRepository::destroy()");
		logger.info("Application is shutting down...");
	}
	
	@Override
	public List<Book> findAll() {
		logger.info("In InMemoryBookRepository::findAll() <-");
		return new ArrayList<Book>(books.values());
	}

	@Override
	public Book findByIsbn(String isbn) {
		logger.info("In InMemoryBookRepository::findByIsbn() <-");
		return books.get(isbn);
	}

	@Override
	public Book findById(Long id) {
		logger.info("In InMemoryBookRepository::findById() <-");
		for(Book book:books.values()){
			if(book.getId()==id){
				return book;
			}
		}
		return null;
	}

	@Override
	public void saveBook(Book book) {
		logger.info("In InMemoryBookRepository::saveBook() <-");
		books.put(book.getIsbn(), book);
		bookAddedEvent.fire(book);
		
	}

}
