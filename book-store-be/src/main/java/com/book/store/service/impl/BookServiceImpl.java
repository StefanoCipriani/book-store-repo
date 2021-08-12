package com.book.store.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.store.dto.AuthorDto;
import com.book.store.dto.BookDto;
import com.book.store.entity.Author;
import com.book.store.entity.Book;
import com.book.store.entity.Editore;
import com.book.store.repositories.BookRepository;
import com.book.store.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<BookDto> findAll(){
		List<BookDto> books = bookRepository.findAll().stream().map(book -> new BookDto(book)).collect(Collectors.toList());

        return books;
	}
	
	
	@Override
	public int insertBooks(List<BookDto> books){

		
		log.info(books.toString());
		List<Book> booksEntity = mapBookDtoListToBookList(books);
		
		List<Book> saved = bookRepository.saveAll(booksEntity);
		return saved.size();
	}
	
	@Override
	public int deleteBook(BookDto book) {
		List<Book> books = bookRepository.findBookByIsbnOrTitoloIgnoreCase(book.getIsbn(), book.getTitolo());
		int count=0;
		for(Book b : books) {
			bookRepository.delete(b);
			count++;
		}
		return count;
	}
	
	
	@Override
	public int updateBook(String isbn, String titolo, BookDto book) throws Exception {
		List<Book> books = titolo == null || titolo.equals("") ? bookRepository.findBookByIsbn(isbn) :  bookRepository.findBookByIsbnOrTitoloIgnoreCase(isbn, titolo);
		if (books == null)
			throw new Exception("Libro non presente");
		int count=0;
		for(Book b : books) {
			b.setTitolo(book.getTitolo());
			b.setIsbn(book.getIsbn());
			bookRepository.save(b);
			count++;
		}
		return count;
	}
	
	private List<Book> mapBookDtoListToBookList(List<BookDto> books) {
		List<Book> booksEntity = new ArrayList<>();
		
		for(BookDto b : books) {
			Book book = new Book();
			book.setIsbn(b.getIsbn());
			book.setTitolo(b.getTitolo());
			
			List<AuthorDto> autori = b.getAutori();
			Set<Author> autoriSet = new HashSet<>();
			
			for(AuthorDto a : autori) {
				Author autore = new Author();
				autore.setNome(a.getNome());
				autore.setCognome(a.getCognome());
				autoriSet.add(autore);
			}
			
			book.setAutori(autoriSet);
			
			Editore editore = new Editore();
			editore.setDescrizione(b.getEditore().getDescrizione());
			editore.setNome(b.getEditore().getNome());
			book.setEditore(editore);
			booksEntity.add(book);
		}
		return booksEntity;
	}
	
	
}
