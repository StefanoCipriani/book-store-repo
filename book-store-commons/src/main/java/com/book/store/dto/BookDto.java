package com.book.store.dto;

import java.util.ArrayList;
import java.util.List;

import com.book.store.entity.Author;
import com.book.store.entity.Book;
import com.book.store.entity.BookAuthors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookDto {

	private String isbn;
	private String titolo;
	private List<AuthorDto> autori;
	private EditoreDto editore;
	
	public BookDto(Book book) {

		this.setIsbn(book.getIsbn());
		this.setTitolo(book.getTitolo());
		List<AuthorDto> autoriDto = this.mapAutoriToAutoriDto(book);
		this.setAutori(autoriDto);
		EditoreDto editore = new EditoreDto();
		editore.setDescrizione(book.getEditore().getDescrizione());
		editore.setNome(book.getEditore().getNome());
		this.setEditore(editore);

	}
	
	
	public List<AuthorDto> mapAutoriToAutoriDto(Book book) {
		List<AuthorDto> autori = new ArrayList<>();
		
		for(BookAuthors a : book.getBookAuthors()) {
			Author author = a.getAuthor();
			AuthorDto  autore = new AuthorDto(); 
			autore.setNome(author.getNome());
			autore.setCognome(author.getCognome());
			autori.add(autore);
		}

		return autori;
	}
	
}
