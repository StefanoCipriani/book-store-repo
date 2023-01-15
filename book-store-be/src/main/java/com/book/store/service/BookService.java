package com.book.store.service;

import java.util.List;

import com.book.store.dto.BookDto;
import com.book.store.entity.Book;

public interface BookService {

	List<BookDto> findAll() throws Exception;
	List<BookDto> insertBooks(List<BookDto> books) throws Exception;
	int deleteBook(BookDto book) throws Exception;
	int updateBook(String isbn, String titolo, BookDto book) throws Exception;
	int updateBook(BookDto book) throws Exception;
}
