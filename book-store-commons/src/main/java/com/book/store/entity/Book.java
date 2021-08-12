package com.book.store.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="BOOK")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal id;
	private String isbn;
	private String titolo;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
	  name = "book_authors", 
	  joinColumns = @JoinColumn(name = "isbn"), 
	  inverseJoinColumns = @JoinColumn(name = "id"))
	private Set<Author> autori;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="editore_id", nullable=false)
	private Editore editore;
	
}
