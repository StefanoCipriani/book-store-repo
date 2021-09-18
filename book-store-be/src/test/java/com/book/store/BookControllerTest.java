package com.book.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.book.store.controller.BookStoreController;
import com.book.store.controller.TestController;
import com.book.store.dto.BookDto;
import com.book.store.service.BookService;
	
@WebMvcTest(value = {TestController.class,BookStoreController.class})
public class BookControllerTest {
	
	private static final Logger log = LoggerFactory.getLogger(BookControllerTest.class);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	private String expectedRes = "[ { isbn: \"12\", titolo: \"mod\", autori: [ { cognome: \"string\", nome: \"string\" } ], editore: { nome: \"string\", descrizione: \"string\" }} ]";
	@Test
	public void test_testController() throws Exception {
		log.info("Begin test_testController()");
		RequestBuilder req = MockMvcRequestBuilders
					.get("/test")
					.accept(MediaType.APPLICATION_JSON);
		MvcResult res = mockMvc.perform(req).andReturn();
		assertEquals("OK", res.getResponse().getContentAsString());
		log.info("End test_testController()");
	}
	
	@Test
	public void test_bookController() throws Exception {
		log.info("Begin test_bookController()");
		
		BookDto bookDto = new BookDto();
		bookDto.setIsbn("12");
		bookDto.setTitolo("mod");
		List<BookDto> books = new ArrayList<>();
		books.add(bookDto);
		when(bookService.findAll()).thenReturn(books);
		
		RequestBuilder req = MockMvcRequestBuilders
					.get("/book-store/books")
					.accept(MediaType.APPLICATION_JSON);
		
		MvcResult res = mockMvc.perform(req)
					.andExpect(status().isOk())
					.andExpect(content().json( "[{ isbn: \"12\", titolo: \"mod\"}]"))
					.andReturn();
		
		log.info("End test_bookController()");
	}
}
