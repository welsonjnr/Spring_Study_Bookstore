package com.estudospring.livraria.dto;

import java.io.Serializable;

import com.estudospring.livraria.domain.Loan;


public class LoanNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer bookId;
	private Integer clientId;

	public LoanNewDTO() {
	}
	
	public LoanNewDTO(Loan loan) {
		this.id = loan.getId();
		this.bookId = loan.getBook().getId();
		this.clientId = loan.getClient().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
}
