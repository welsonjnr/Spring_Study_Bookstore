package com.estudospring.livraria.dto;

import java.io.Serializable;
import java.util.Date;

import com.estudospring.livraria.domain.Loan;

public class LoanDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer status;
	private Date loanDay;
	
	private Integer bookId;
	private String nameBook;
	private Integer clientId;
	private String nameClient;

	public LoanDTO() {
	}
	
	public LoanDTO(Loan objLoan) {
		super();
		setId(objLoan.getId());
		status = objLoan.getStatus();
		loanDay = objLoan.getLoanDay();
		bookId = objLoan.getBook().getId();
		nameBook = objLoan.getBook().getName();
		clientId = objLoan.getClient().getId();
		nameClient = objLoan.getClient().getNameClient();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getNameBook() {
		return nameBook;
	}

	public void setNameBook(String nameBook) {
		this.nameBook = nameBook;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
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
	
}
