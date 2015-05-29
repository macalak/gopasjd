package ite.librarymaster.web;

import ite.librarymaster.model.Book;
import ite.librarymaster.service.LibraryServiceLocal;

import java.util.List;

import javax.ejb.EJB;

@javax.faces.bean.ManagedBean(name="booksController")
@javax.faces.bean.RequestScoped
public class BooksController {
	
	@EJB
	LibraryServiceLocal libraryService;
	
	public List<Book> getAllBooks() {
		return libraryService.getAllBooks();
	}
}
