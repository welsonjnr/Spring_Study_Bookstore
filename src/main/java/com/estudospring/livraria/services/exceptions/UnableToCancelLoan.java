package com.estudospring.livraria.services.exceptions;

public class UnableToCancelLoan extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UnableToCancelLoan(String msg) {
		super(msg);
	}

	public UnableToCancelLoan(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}