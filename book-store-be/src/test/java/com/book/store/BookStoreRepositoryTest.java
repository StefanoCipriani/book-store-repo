package com.book.store;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import com.book.store.entity.Author;
import com.book.store.entity.Book;
import com.book.store.entity.BookAuthors;
import com.book.store.entity.Editore;
import com.book.store.helper.TestHelper;
import com.book.store.repositories.BookRepository;

@DataJpaTest(showSql = true)
@DirtiesContext
public class BookStoreRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(BookStoreRepositoryTest.class);

	@Autowired
	private BookRepository bookRepository;

	//@Test
	public void testFacade() {
		int booksNum = bookRepository.findAll().size();
		assertTrue(booksNum == 0);
		Book book = TestHelper.creaLibroHelper(false);
		insertBookHelper(book);
		booksNum = bookRepository.findAll().size();
		assertTrue(booksNum == 1);
		List<Book> searchList = search_test("1");
		assertTrue(searchList.size() == 1);
		delete_test(book);
		booksNum = bookRepository.findAll().size();
		assertTrue(booksNum == 0);
	}

	@Test
	@Transactional
	public void insert_Test() {
		log.info("Inizio - Test inserimento libro\n\n\n");
		bookRepository.deleteAll();
		Book book = TestHelper.creaLibroHelper(false);
		Book bookInserted = insertBookHelper(book);
		assertTrue(bookInserted.getIsbn().equals("1"));
		log.info("Fine - Test inserimento libro\n\n\n");
	}

	@Test
	@Transactional
	public void insert_multiple_authors_Test() {
		log.info("Inizio - Test inserimento autori multipli");
		bookRepository.deleteAll();
		Book book = TestHelper.creaLibroHelper(true);
		Book bookInserted = insertBookHelper(book);
		assertTrue(bookInserted.getBookAuthors().size() == 2);
		log.info("Inizio - Fine inserimento autori multipli");
	}
	
	
	@Test
	@Transactional
	public void update_book_Test() {
		log.info("Inizio - Test aggiornamento libro");
		bookRepository.deleteAll();
		Book book = TestHelper.creaLibroHelper(true);
		
		
		Book bookInserted = insertBookHelper(book);
		//Aggiorno Titolo
		bookInserted.setTitolo(bookInserted.getTitolo()+" upd");
		//Aggiorno editore
		Editore editore = bookInserted.getEditore();
		editore.setNome(editore.getNome()+" upd");
		editore.setDescrizione(editore.getDescrizione()+" upd");
		bookInserted.setEditore(editore);
		//Aggiorno autori
		Set<BookAuthors> bookAuthorsSet = bookInserted.getBookAuthors();
		for (BookAuthors bookAuthors : bookAuthorsSet) {
			Author author = bookAuthors.getAuthor();
			author.setCognome(author.getCognome()+" upd");
			author.setNome(author.getNome()+" upd");
		}
		
		//Aggiorno il libro
		Book bookUpd = bookRepository.save(bookInserted);
		log.info("Verifica esito aggiornamento\n\n");
		log.info("titolo -> {}, editore(nome, descrizione) -> ({}, {})", bookUpd.getTitolo(),bookUpd.getEditore().getNome(), bookUpd.getEditore().getDescrizione() );
		assertTrue(bookUpd.getTitolo().contains("upd"));
		assertTrue(bookUpd.getEditore().getNome().contains("upd"));
		assertTrue(bookUpd.getEditore().getDescrizione().contains("upd"));
		
		for(BookAuthors ba : bookUpd.getBookAuthors()) {
			assertTrue(ba.getAuthor().getCognome().contains("upd"));
			assertTrue(ba.getAuthor().getNome().contains("upd"));
			
			log.info("autore(nome, cognome) -> ({}, {})", ba.getAuthor().getNome(), ba.getAuthor().getCognome() );
		}
		
		
		log.info("Fine - Test aggiornamento libro");
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
	
	private Book insertBookHelper(Book book) {
		log.info("Begin insert_test");
		Book bookSaved = bookRepository.saveAndFlush(book);
		log.info("End insert_test");
		return bookSaved;
	}
	
	
	
	/*
	@Test
	public void update_test() {
		log.info("Begin update book test");
		
		//Crea un nuovo libro
		Book book = new Book();
		book.setIsbn("2");
		book.setTitolo("Test");
		Editore editore = new Editore();
		editore.setDescrizione("Test");
		editore.setNome("Test");
		book.setEditore(editore);
		Set<Author> autori = new HashSet<>();
		Author a = new Author();
		a.setNome("Test1");
		a.setCognome("Test1");
		autori.add(a);
		a.setNome("Test2");
		a.setCognome("Test2");
		autori.add(a);
		book.setAutori(autori);
		//Inserisci libro
		insert_test(book);
			
		//Modifica i dati del libro tranne l'ISBN
		book.setTitolo(book.getTitolo()+" upd");
		editore = book.getEditore();
		editore.setNome(editore.getNome()+" upd");
		editore.setDescrizione(editore.getDescrizione()+" upd");
		autori = book.getAutori();
		for (Author autore : autori) {
			autore.setCognome(autore.getCognome()+"upd");
			autore.setNome(autore.getNome()+"upd");
		}
		
		
		Book bookUpdated = bookRepository.save(book);
	
		assertTrue(bookUpdated.getTitolo().contains("upd"));
		assertTrue(bookUpdated.getEditore().getDescrizione().contains("upd"));
		assertTrue(bookUpdated.getEditore().getNome().contains("upd"));
		
		
		for (Author autore : bookUpdated.getAutori()) {
			assertTrue(autore.getCognome().contains("upd"));
			assertTrue(autore.getNome().contains("upd"));
		}
		
		log.info("End search_test");
		
	}
	
	
	*/
}
