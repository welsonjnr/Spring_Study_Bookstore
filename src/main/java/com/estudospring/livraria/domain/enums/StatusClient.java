package com.estudospring.livraria.domain.enums;

public enum StatusClient {

	DISPONIVEL(1, "Emprestimo Disponível"),
	PENDENTE(2, "Emprestimo Pendente");
	
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
