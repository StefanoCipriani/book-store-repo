package com.book.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BookStoreBeApplication{


	public static void main(String[] args) {
		log.info("Applicazione in caricamento");
		SpringApplication.run(BookStoreBeApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			// save a few customers
			repository.save(new Book("1234", "Bauer"));
			repository.save(new Book("12345", "Bauer"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Book customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Optional<Book> book = repository.findById("1234");
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(book.get().toString());
			log.info("");

		};
	}

	*/
}
