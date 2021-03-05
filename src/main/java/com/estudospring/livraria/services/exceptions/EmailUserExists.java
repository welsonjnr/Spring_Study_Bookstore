package com.estudospring.livraria.services.exceptions;

public class EmailUserExists extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailUserExists(String msg) {
		super(msg);
	}

}
