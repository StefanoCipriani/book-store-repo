package com.book.store.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.dto.BookDto;
import com.book.store.entity.Book;
import com.book.store.service.BookService;

@RestController
@RequestMapping("book-store")
@CrossOrigin
public class BookStoreController {

	private static final Logger log = LoggerFactory.getLogger(BookStoreController.class);



	@Autowired
	private BookService bookService;


	@GetMapping("books")
	public ResponseEntity<List<BookDto>> getAllBooks(){ 		
		try {
			return new ResponseEntity<List<BookDto>>(bookService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<BookDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("books")
	public ResponseEntity<List<BookDto>> insertBooks(@RequestBody List<BookDto> books){ 

		log.info(books.toString());
		try {
			List<BookDto> insertedBooks = bookService.insertBooks(books);
			return new ResponseEntity<List<BookDto>>(insertedBooks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<BookDto>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("books")
	public ResponseEntity<Integer> deleteBook(@RequestBody BookDto book){ 	
		log.info(book.toString());

		try {
			int deleteBook = bookService.deleteBook(book);
			return new ResponseEntity<Integer>(deleteBook, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("books/delete-all")
	public ResponseEntity<String> deleteAllBook() throws Exception{ 	
		log.info("Svuoto la libreria");

		List<BookDto> allBooks = bookService.findAll();
		for (BookDto bookDto : allBooks) {
			try {
					bookService.deleteBook(bookDto);
			}catch(Exception e) {
					return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return new ResponseEntity<String>("Libreria svuotata", HttpStatus.OK);
	}

	@PutMapping("books")
	public ResponseEntity<Integer> updateBook(@RequestBody List<BookDto> books){ 
		log.info(books.toString());
		for (BookDto bookDto : books) {
			try {
					bookService.updateBook(bookDto);
			}catch(Exception e) {
					return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	
	@PutMapping("books/{isbn}")
	public ResponseEntity<Integer> updateBooks(@PathVariable String isbn, @RequestParam(required = false) String titolo, @RequestBody List<BookDto> books){ 

		log.info(books.toString());
		try {
			int updateBooks = bookService.updateBook(isbn, titolo, books.get(0));
			return new ResponseEntity<Integer>(updateBooks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
