package com.estudospring.livraria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Address;
import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.domain.enums.UserType;
import com.estudospring.livraria.dto.ClientNewDTO;
import com.estudospring.livraria.repositories.ClientRepository;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repoCli;
	
	public Client find(Integer id) {
		Optional<Client> obj = repoCli.findById(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Id: " +  id +
					", Tipo: " + Client.class.getName());
		}
		return obj.orElse(null);
	}
	
	/*
	 * Estou pegando um objeto ClientNewDto e colocando as informações dentro dele
	 * Como a implementação vai ser feita depois do objeto for instaciado, não vou precisar coloca-lo aqui. Mas, não pesquisa do JSON
	 * Aqui também tem de fazer as implementações e ligações padrões, como relacionar o address em client
	 */
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(),objDto.getEmail(), objDto.getCpf(), objDto.getCourse(), objDto.getInstitution(), objDto.getPeriod(), UserType.toEnum(objDto.getType()));
		Address adr = new Address(null, objDto.getAvenue(), objDto.getNumber(), objDto.getBairro(), null, null);
		cli.getAddress().add(adr);
		cli.getPhones().add(objDto.getPhone1());
		if(objDto.getPhone2() != null) {
			cli.getPhones().add(objDto.getPhone2());
		}
		if(objDto.getPhone3() != null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
		
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
}
