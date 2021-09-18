package com.book.store;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.book.store.entity.Author;
import com.book.store.entity.Book;
import com.book.store.entity.Editore;
import com.book.store.repositories.BookRepository;

@DataJpaTest
public class BookStoreRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(BookStoreRepositoryTest.class);

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testFacade() {
		int booksNum = findAll_test();
		assertTrue(booksNum == 0);
		Book book = new Book();
		book.setIsbn("1");
		book.setTitolo("Test");
		Editore editore = new Editore();
		editore.setDescrizione("Einauidi");
		editore.setNome("Einaudi");
		book.setEditore(editore);
		Set<Author> autori = new HashSet<>();
		Author a = new Author();
		a.setNome("Stefano");
		a.setCognome("Cipriani");
		book.setAutori(autori);
		insert_test(book);
		booksNum = findAll_test();
		assertTrue(booksNum == 1);
		List<Book> searchList = search_test("1");
		assertTrue(searchList.size() == 1);
		delete_test(book);
		booksNum = findAll_test();
		assertTrue(booksNum == 0);
	}


	private int findAll_test() {
		log.info("Begin findAll");
		List<Book> findAll = bookRepository.findAll();
		log.info("End findAll");
		return findAll.size();

	}


	private void insert_test(Book book) {
		log.info("Begin insert_test");
		bookRepository.saveAndFlush(book);
		log.info("End insert_test");

	}


	private List<Book> search_test(String isbn) {
		log.info("Begin search_test");
		List<Book> findBookByIsbn = bookRepository.findBookByIsbn(isbn);
		log.info("End search_test");
		return findBookByIsbn;

	}


	private void delete_test(Book book) {
		log.info("Begin delete test");
		bookRepository.delete(book);
		log.info("End delete test");

	}
}
