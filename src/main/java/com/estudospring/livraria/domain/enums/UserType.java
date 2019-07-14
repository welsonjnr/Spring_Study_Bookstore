package com.estudospring.livraria.domain.enums;

public enum UserType {

	STUDENT(1, "Estudante"),
	TEACHER(2, "Professor");
	
	private int cod;
	private String descricao;
	
	private UserType(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public static UserType toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(UserType x: UserType.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}
	
}
