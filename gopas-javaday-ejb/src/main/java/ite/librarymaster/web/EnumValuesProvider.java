package ite.librarymaster.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ite.librarymaster.model.BookGenre;
import ite.librarymaster.model.MediumAvailability;

@Named
@RequestScoped
public class EnumValuesProvider {
	
	public BookGenre[] getBookGenres(){
		return BookGenre.values();
	}
	
	public MediumAvailability[] getMediumAvailabilities(){
		return MediumAvailability.values();
	}

}
