 package com.estudospring.livraria.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.services.validation.ClientUpdate;
//Client Data Transfer Object
@ClientUpdate
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message="Required!")
	@Length(min=5, max=80, message="Length must be between 5 and 80 characters!!")
	private String name;
	@NotEmpty(message="Required!")
	@Email(message="Invalid Email")
	private String email;
	private String course;
	private String institution;
	private Integer period;
	
	public ClientDTO() {
	}

	public ClientDTO(Client objDto) {
		super();
		this.id = objDto.getId();
		this.name = objDto.getNameClient();
		this.email = objDto.getEmail();
		this.course = objDto.getCourse();
		this.institution = objDto.getInstitution();
		this.period = objDto.getPeriod();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

}
