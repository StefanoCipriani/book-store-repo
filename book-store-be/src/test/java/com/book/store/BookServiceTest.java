package com.book.store;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.book.store.dto.BookDto;
import com.book.store.entity.Book;
import com.book.store.helper.TestHelper;

import com.book.store.repositories.BookRepository;
import com.book.store.service.impl.BookServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = {BookServiceImpl.class,BookRepository.class })
public class BookServiceTest {

	@Autowired
	private BookServiceImpl bookService;

	@MockBean
	private BookRepository bookRepository;


	@Test
	public void test_insert_books() throws Exception{
		log.info("Inizio test -> test_insert_books");

		List<BookDto> books = new ArrayList<>();
		Book book=null;
		
		for(int i=0; i<2; i++) {
			book = TestHelper.creaLibroHelper(false, UUID.randomUUID().toString());
			BookDto bookDto = new BookDto(book);
			books.add(bookDto);	
		}

		when(bookRepository.saveAll(bookService.mapBookDtoListToBookList(books))).thenReturn(bookService.mapBookDtoListToBookList(books));
		List<BookDto> insertedBooks = bookService.insertBooks(books);
		assertEquals(books.size(),insertedBooks.size());
		log.info("Fine test -> test_insert_books");

	}
}
