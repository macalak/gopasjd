package ite.librarymaster.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import ite.librarymaster.dao.BookRepository;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiTestRunner.class)
public class LibraryServiceBeanTest {
	
	@Inject
	LibraryService testedObject;
	
//	@Inject
//	BeanManager beanManager;
	
	@Test
	public void getAllBooksTest(){
//		Set<Bean<?>> beans = beanManager.getBeans(BookRepository.class);
		assertNotNull(testedObject);
		assertEquals(4,testedObject.getAllBooks().size());
		
	}

}
