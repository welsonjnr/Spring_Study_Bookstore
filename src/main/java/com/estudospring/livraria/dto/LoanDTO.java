package com.estudospring.livraria.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.estudospring.livraria.domain.Loan;
import com.estudospring.livraria.domain.enums.LoanStatus;

public class LoanDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private LoanStatus status;
	private String loanDay;
	private String loanReturnDay;
	
	private Integer bookId;
	private String nameBook;
	private Integer clientId;
	private String nameClient;

	public LoanDTO() {
	}
	
	public LoanDTO(Loan objLoan) {
		super();
		setId(objLoan.getId());
		status = objLoan.getLoanStatus();
		loanDay = objLoan.getLoanDay();
		loanReturnDay = objLoan.getLoanDay();
		bookId = objLoan.getBook().getId();
		nameBook = objLoan.getBook().getName();
		clientId = objLoan.getClient().getId();
		nameClient = objLoan.getClient().getName();
	}

	public LoanStatus getStatus() {
		return status;
	}

	public void setStatus(LoanStatus status) {
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

	public String getLoanDay() {
		return loanDay;
	}

	public void setLoanDay(String loanDay) {
		this.loanDay = loanDay;
	}

	public String getLoanReturnDay() {
		return loanReturnDay;
	}

	public void setLoanReturnDay(String loanReturnDay) {
		this.loanReturnDay = loanReturnDay;
	}
}
