package com.estudospring.livraria.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


import org.hibernate.validator.constraints.Length;

import com.estudospring.livraria.domain.Client;

public class ClientDTO {

	private Integer id;
	@NotEmpty(message="Required!")
	@Length(min=5, max=80, message="Length must be between 5 and 80 characters!!")
	private String name;
	private String course;
	private String instituiton;
	@NotEmpty(message="Required!")
	@Email(message="Invalid Email")
	private String email;
	
	public ClientDTO() {
	}

	public ClientDTO(Client objDto) {
		super();
		this.id = objDto.getId();
		this.name = objDto.getName();
		this.course = objDto.getCourse();
		this.instituiton = objDto.getInstitution();
		this.email = objDto.getEmail();
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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getInstituiton() {
		return instituiton;
	}

	public void setInstituiton(String instituiton) {
		this.instituiton = instituiton;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
