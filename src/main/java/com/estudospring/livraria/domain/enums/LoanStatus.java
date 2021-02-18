package com.estudospring.livraria.domain.enums;

public enum LoanStatus {
	
	OK (1, "Ok"),
	DEVOLVIDO (2, "Devolvido"),
	CANCELADO(3, "Cancelado"),
	RENOVADO(4, "Renovado"),
	ATRASADO(5, "Atrasado");
	
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
