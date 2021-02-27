package com.estudospring.livraria.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.estudospring.livraria.domain.enums.BookStatus;
import com.estudospring.livraria.domain.enums.StatusClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message="Required!")
	private String name;
	@NotEmpty(message="Required!")
	private String author;
	private String edition;
	private Integer bookStatus;
	@NotNull(message="Required!")	
	private Integer amount;

	@ManyToOne
	@JoinColumn(name="Category_id")
	private Category category;

	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<Loan> loans = new ArrayList<>();

	public Book() {
	}

	public Book(Integer id, String name, String author, String edition, BookStatus bookStatus,Integer amount, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.edition = edition;
		this.bookStatus = (bookStatus==null) ? null : bookStatus.getCod();
		this.amount = amount;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}
	
	public BookStatus getBookStatus() {
		return BookStatus.toEnum(bookStatus);
	}

	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus.getCod();
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}