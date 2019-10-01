package com.estudospring.livraria.services.exceptions;

public class BookIsNotAvailableForRenewal extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BookIsNotAvailableForRenewal(String msg) {
		super(msg);
	}

	public BookIsNotAvailableForRenewal(String msg, Throwable cause) {
		super(msg, cause);
	}
}
