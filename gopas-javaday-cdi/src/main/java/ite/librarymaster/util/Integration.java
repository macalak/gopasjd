package ite.librarymaster.util;

import ite.librarymaster.dao.Added;
import ite.librarymaster.model.Book;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

public class Integration {
	@Inject
	private Logger logger;
	
	@SuppressWarnings("unused")
	private void onBookAdded(@Observes @Added Book book){
		logger.info("EVENT: Book was added, {}",book);
	}

}
