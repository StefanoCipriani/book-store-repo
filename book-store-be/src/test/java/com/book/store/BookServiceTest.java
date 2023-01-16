package com.book.store;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.book.store.dto.BookDto;
import com.book.store.entity.Author;
import com.book.store.entity.Book;
import com.book.store.entity.Editore;
import com.book.store.repositories.BookRepository;
import com.book.store.service.impl.BookServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookServiceImpl bookService;
	
	@Test 
	public void findAll_Test() {
		log.info("Performing test...");
		Book book = new Book();
		book.setIsbn("123");
		book.setId(new BigDecimal(123));
		book.setAutori(new HashSet<Author>());
		book.setEditore(new Editore(new BigDecimal(1), "NomeEditore", "CognomeEditore", new HashSet<Book>()));
		book.setTitolo("Titolo");
		
		List<Book> listBook = new ArrayList<>();
		listBook.add(book);
		
		when(bookRepository.findAll()).thenReturn(listBook);
		
		try {
			List<BookDto> findAll = bookService.findAll();
			assertTrue(findAll.size() == 1);
			log.info("Test succesfull...");
		} catch (Exception e) {
			log.error("Exception: {} {}", e.getMessage(), e);
		}
		
	}
}
