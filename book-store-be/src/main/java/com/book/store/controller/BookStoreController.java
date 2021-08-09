package com.book.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.dto.BookDto;

@RestController
@RequestMapping("book-store")
public class BookStoreController {

	List<BookDto> bookRepo = new ArrayList<>();
	
	@PostConstruct
	public void initBookRepo() {
		BookDto bookDto = new BookDto();
		bookDto.setTitolo("Il nome della rosa");
		bookRepo.add(bookDto);
		bookDto = new BookDto();
		bookDto.setTitolo("Stoner");
		bookRepo.add(bookDto);
	}
	@GetMapping("books")
	public ResponseEntity<List<BookDto>> getAllBooks(){ 
		
		return new ResponseEntity<List<BookDto>>(bookRepo, HttpStatus.OK);
	}
}
