package com.book.store.entity;

import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Data
@Table(name="BOOK_AUTHORS")
public class BookAuthors {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "book_id", referencedColumnName = "id")
	private Book book;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "author_id", referencedColumnName = "id")
	private Author author;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookAuthors other = (BookAuthors) obj;
		return Objects.equals(author, other.author) && Objects.equals(book, other.book) && Objects.equals(id, other.id);
	}
	
	
	
	
	
	
}
