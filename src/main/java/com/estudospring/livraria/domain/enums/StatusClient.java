package com.estudospring.livraria.domain.enums;

public enum StatusClient {

	RELEASED(1, "Loan released"),
	PENDING(2, "Loan Pending");
	
	private int cod;
	private String descricao;
	
	private StatusClient(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public static StatusClient toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(StatusClient x: StatusClient.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}
	
}
