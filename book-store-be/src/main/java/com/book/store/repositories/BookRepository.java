package com.book.store.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.store.entity.Book;



public interface BookRepository extends JpaRepository<Book, String> {
	List<Book> findBookByIsbnOrTitoloIgnoreCase(String isbn, String titolo);
	List<Book> findBookByIsbn(String isbn);
}
