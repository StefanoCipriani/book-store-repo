package com.book.store.dto;

import java.util.ArrayList;
import java.util.List;

import com.book.store.entity.Author;
import com.book.store.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookDto {

	public BookDto(Book book) {

		this.setIsbn(book.getIsbn());
		this.setTitolo(book.getTitolo());

		List<AuthorDto> autori = new ArrayList<>();
		for(Author a : book.getAutori()) {
			AuthorDto  autore = new AuthorDto(); 
			autore.setNome(a.getNome());
			autore.setCognome(a.getCognome());
			autori.add(autore);
		}

		this.setAutori(autori);

		EditoreDto editore = new EditoreDto();
		editore.setDescrizione(book.getEditore().getDescrizione());
		editore.setNome(book.getEditore().getNome());
		this.setEditore(editore);

	}
	private String isbn;
	private String titolo;
	private List<AuthorDto> autori;
	private EditoreDto editore;
}
