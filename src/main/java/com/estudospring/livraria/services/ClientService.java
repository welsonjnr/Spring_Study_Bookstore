package com.estudospring.livraria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Client;
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
}
