package com.book.store.entity;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private BigInteger id;
	private String isbn;
	private String titolo;
	private String genere;
	private String tipologia;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<BookAuthors> bookAuthors = new HashSet<>();
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="editore_id", nullable=false)
	private Editore editore;
	
}
