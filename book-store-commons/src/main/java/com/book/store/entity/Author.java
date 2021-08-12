package com.book.store.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="AUTHOR")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal id;
	private String cognome;
	private String nome;
	//@ManyToMany(mappedBy="autori", fetch = FetchType.LAZY)
	//private Set<Book> book;
	
}
