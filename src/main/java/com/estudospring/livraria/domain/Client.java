package com.estudospring.livraria.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.estudospring.livraria.domain.enums.StatusClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
	
@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nameClient;
	private String cpf;
	private String course;
	private String institution;
	private String email;
	private Integer period;
	private Integer statusClient;

	@OneToMany(mappedBy="client", cascade=CascadeType.ALL)
	private List<Address> address = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="Phones")
	private Set<String> phones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="client")
	private List<Loan> loans = new ArrayList<>();
	
	public Client() {
	}
	
	public Client(Integer id, String nameClient, String cpf, String course, String institution, String email, Integer period,
			StatusClient statusClient) {
		super();
		this.id = id;
		this.nameClient = nameClient;
		this.cpf = cpf;
		this.course = course;
		this.institution = institution;
		this.email = email;
		this.period = period;
		this.statusClient = (statusClient==null) ? null : statusClient.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public StatusClient getStatusClient() {
		return StatusClient.toEnum(statusClient);
	}

	public void setStatusClient(StatusClient statusClient) {
		this.statusClient = statusClient.getCod();
	}
	
	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}