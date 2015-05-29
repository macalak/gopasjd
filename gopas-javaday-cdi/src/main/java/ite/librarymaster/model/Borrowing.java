package ite.librarymaster.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class Borrowing implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date borrowed;
	
	// Unidirectional One-To-Many relationship 
	@OneToMany
	private Collection<Book> books;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(Date borrowed) {
		this.borrowed = borrowed;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
	
}
