package com.book.store.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.entity.Author;
import com.book.store.entity.Book;
import com.book.store.entity.BookAuthors;
import com.book.store.entity.Editore;
import com.book.store.repositories.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestController {

	@Autowired 
	private BookRepository bookRepository;
	
	@Value("${app.docker-profile-description:variable not setted}")
	String dockerProfile;
	
	@GetMapping("test")
	public String test() { 
		
		log.info("docker-profile value -> {}", dockerProfile);
		bookRepository.deleteAll();
		Book book = new Book();
	
		book.setIsbn("bababaab");
		book.setTitolo("TestInserimento");
		Editore editore = new Editore();
		editore.setDescrizione("Einauidi");
		editore.setNome("Einaudi");
		book.setEditore(editore);
		
		Author a = new Author();
		a.setNome("Stefano");
		a.setCognome("Cipriani");
		
		Set<BookAuthors> baSet = new HashSet<>(); 
		BookAuthors ba = new BookAuthors();
		ba.setAuthor(a);		
		ba.setBook(book);
		
		baSet.add(ba);
		
		book.setBookAuthors(baSet);
		
		bookRepository.save(book);
		return "DAi";
	}
}
