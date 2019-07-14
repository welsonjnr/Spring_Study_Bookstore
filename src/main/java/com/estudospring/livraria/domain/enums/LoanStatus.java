package com.estudospring.livraria.domain.enums;

public enum LoanStatus {
	
	BORROWED (1, "Borrowed"),
	RETURNED (2, "Returned"),
	CANCELED(3, "Canceled"),
	LATE (4, "Late");
	
	private int cod;
	private String status;
	
	
	private LoanStatus(int cod, String status) {
		this.cod = cod;
		this.status = status;
	}


	public int getCod() {
		return cod;
	}


	public void setCod(int cod) {
		this.cod = cod;
	}


	public String getStatus() {
		return status;
	}


	public static LoanStatus toEnum (Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(LoanStatus x : LoanStatus.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}
	
	
}
