package com.estudospring.livraria.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

//Vou precisar fazer uma nova anotação para que a classe ClientNewDTO, posso transformar um Client em ClientDTO. Para eu poder mandar para o banco de dados para ser feito o CRUD

//Data Transfer Object
public class ClientNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Required!")
	@Length(min = 5, max = 80, message = "Name length must be between 5 and 80 characters")
	private String name;

	@NotEmpty(message = "Required!")
	private String cpf;

	@NotEmpty(message = "Required!")
	@Email(message="Email Inválido")
	private String email;

	private String course;
	private String institution;
	private Integer period;
	private Integer type;
	
	private String avenue;
	private Integer number;
	private String bairro;

	private String phone1;
	private String phone2;
	private String phone3;

	private Integer cityId;

	public ClientNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAvenue() {
		return avenue;
	}

	public void setAvenue(String avenue) {
		this.avenue = avenue;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

}
