package com.estudospring.livraria.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.estudospring.livraria.domain.Category;

public class CategoryDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message="Required!")
	@Length(min=5, max=80, message="\r\n" + 
			"Length must be between 5 and 80 characters")
	private String name;

	public CategoryDTO() {
	}

	/* Construtor do que vai ser utilizado como inserção, pesquisa, edição no banco
	de dados
	*/
	public CategoryDTO(Category obj) {
		id = obj.getId();
		name = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}