package com.book.store.helper;

import java.util.HashSet;
import java.util.Set;

import com.book.store.entity.Author;
import com.book.store.entity.Book;
import com.book.store.entity.BookAuthors;
import com.book.store.entity.Editore;

public class TestHelper {

	public static Book creaLibroHelper(boolean hasMultipleAuthors) {
		return creaLibroHelper(hasMultipleAuthors, null);
	}
	
	public static Book creaLibroHelper(boolean hasMultipleAuthors, String isbn) {
		Book book = new Book();
		
		book.setIsbn(isbn == null ? "1" : isbn);
		book.setTitolo("TestInserimento");
		Editore editore = new Editore();
		editore.setDescrizione("Einauidi");
		editore.setNome("Einaudi");
		book.setEditore(editore);
		
		Set<BookAuthors> baSet = new HashSet<>(); 
		
		BookAuthors ba = new BookAuthors();
		
		Author a = new Author();
		a.setNome("Stefano");
		a.setCognome("Cipriani");
		ba.setAuthor(a);		
		ba.setBook(book);
		baSet.add(ba);
		
		if(hasMultipleAuthors) {
			ba = new BookAuthors();
			a = new Author();
			a.setNome("Alessandra");
			a.setCognome("Minerva");
			ba.setAuthor(a);
			ba.setBook(book);
			baSet.add(ba);
		}
		
		book.setBookAuthors(baSet);
		baSet.add(ba);
		return book;
	}
}
