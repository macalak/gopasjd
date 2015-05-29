package ite.librarymaster.web;

import ite.librarymaster.model.Book;
import ite.librarymaster.service.LibraryService;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@Named("booksController")
@RequestScoped
public class BooksController {
	
	@Inject
	LibraryService libraryService;
	
	@Inject
	private List<Book> allBooks;
	
	@Produces
	private List<Book> prepareBooks(){
		return libraryService.getAllBooks();
	}
	
	public List<Book> getAllBooks() {
		return allBooks;
	}
}
