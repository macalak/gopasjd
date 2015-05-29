package ite.librarymaster.service;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.junit.Test;

public class LibraryServiceBeanTest {
	
	@Test
	public void simpleTest(){
		Map<String, Object> properties = new HashMap<>();
		properties.put(EJBContainer.MODULES, new File("bin"));
		
		try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {
			Context ctx = ec.getContext();
			LibraryService libraryService =  (LibraryService) ctx.lookup("ejb/LibraryServiceBean");
			assertNotNull(libraryService);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
