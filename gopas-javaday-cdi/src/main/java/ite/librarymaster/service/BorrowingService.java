package ite.librarymaster.service;

import ite.librarymaster.model.Book;

import java.util.List;

public interface BorrowingService {
	
	void borrowBooks(List<Book> books) throws LibraryException;

}
