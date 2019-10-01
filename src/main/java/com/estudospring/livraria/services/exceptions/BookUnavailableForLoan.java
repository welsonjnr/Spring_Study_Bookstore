package com.estudospring.livraria.services.exceptions;

public class BookUnavailableForLoan extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BookUnavailableForLoan(String msg) {
		super(msg);
	}

	public BookUnavailableForLoan (String msg, Throwable cause) {
		super(msg, cause);
	}
}
