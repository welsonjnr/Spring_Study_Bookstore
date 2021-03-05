package com.estudospring.livraria.dto;

import java.io.Serializable;

public class UserLoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String nome;
	private String senha;
	
	public UserLoginDTO() {}
	
	public UserLoginDTO(String email, String nome, String senha) {
		super();
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}

}

