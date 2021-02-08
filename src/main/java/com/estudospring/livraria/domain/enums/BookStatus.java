package com.estudospring.livraria.domain.enums;

public enum BookStatus {

	DISPONIVEL(1, "Disponivel"), EMPRESTADO(2, "Emprestado"), UNICO(3, "Unico");

	private int cod;
	private String status;

	private BookStatus(int cod, String status) {
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

	public static BookStatus toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (BookStatus x : BookStatus.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}

}
