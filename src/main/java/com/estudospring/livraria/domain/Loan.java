package com.estudospring.livraria.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.estudospring.livraria.domain.enums.LoanStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Loan implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Date loanDay;
	private Integer status;
	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="client_id")
	private Client client;
	
	public Loan() {
	}
	
	public Loan(Integer id, Date loanDay, LoanStatus status, Book book, Client client) {
		super();
		this.id = id;
		this.loanDay = loanDay;
		this.status = status.getCod();
		this.book = book;
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getLoanDay() {
		return loanDay;
	}

	public void setLoanDay(Date loanDay) {
		this.loanDay = loanDay;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
		Loan other = (Loan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
