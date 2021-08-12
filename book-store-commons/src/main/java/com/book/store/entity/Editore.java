package com.book.store.entity;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="EDITORE")
public class Editore {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal id;
	private String nome;
	private String descrizione;
	@OneToMany(mappedBy="editore")
	private Set<Book> books;
}
