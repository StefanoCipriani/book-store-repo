package com.book.store.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.store.dto.BookDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("book-store")
@Slf4j
public class PrecaricamentoController {

	
	@GetMapping("precarica")
	public ResponseEntity<List<BookDto>> insertBooks(){ 

		log.info("Precaricamento da file testuale");
		List<BookDto> books = new ArrayList<>();
	
		try {
			//Converti da file a BookDto;
			File file = new File("precaricamento.txt");
			log.info("file.getAbsolutePath(): ->{}", file.getAbsolutePath());
			log.info("file.getCanonicalPath(): ->{}", file.getCanonicalPath());
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			       log.info(line);
			    }
			}
			
			return new ResponseEntity<List<BookDto>>(books, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<BookDto>>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
