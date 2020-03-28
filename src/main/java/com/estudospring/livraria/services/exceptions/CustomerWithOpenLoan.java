package com.estudospring.livraria.services.exceptions;

public class CustomerWithOpenLoan extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CustomerWithOpenLoan(String msg) {
		super(msg);
	}

	public CustomerWithOpenLoan (String msg, Throwable cause) {
		super(msg, cause);
	}
}