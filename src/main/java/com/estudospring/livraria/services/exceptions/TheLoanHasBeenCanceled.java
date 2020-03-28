package com.estudospring.livraria.services.exceptions;

public class TheLoanHasBeenCanceled  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TheLoanHasBeenCanceled(String msg) {
		super(msg);
	}

	public TheLoanHasBeenCanceled(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}