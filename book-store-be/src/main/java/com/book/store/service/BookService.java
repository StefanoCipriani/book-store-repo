package com.book.store.service;

import java.util.List;

import com.book.store.dto.BookDto;

public interface BookService {

	List<BookDto> findAll() throws Exception;
	int insertBooks(List<BookDto> books) throws Exception;
	int deleteBook(BookDto book) throws Exception;
	int updateBook(String isbn, String titolo, BookDto book) throws Exception;

}
