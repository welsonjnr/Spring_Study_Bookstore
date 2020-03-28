package com.estudospring.livraria.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Address;
import com.estudospring.livraria.repositories.AddressRepository;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class AddressService {

	@Autowired
	private ClientService cliServ;
	
	@Autowired
	private CityService citServ;
	
	@Autowired
	private AddressRepository addRepo;
	
	public Address find(Integer id){
		Optional<Address> obj = addRepo.findById(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Address.class.getName());
		}
	
		return obj.orElse(null);
	}
	
	public Address insert(Address adr) {
		adr.setId(null);
		adr.setClient(cliServ.find(adr.getId()));
		adr.setCity(citServ.find(adr.getClient().getId()));
		
		return addRepo.save(adr);
	}
	
	public Address update(Address adr) {
		Address adrUpd = find(adr.getId());
		adrUpd = adr;
		return addRepo.save(adrUpd);
	}
	
	public void remove(Integer id) {
		find(id);
		
		try {
			addRepo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Não foi possível excluir, poís, possuem outras categorias relacionada a essa!");
		}
	}
	
	public Page<Address> findPage(Integer pages, Integer linePerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(pages, linePerPage, Direction.valueOf(direction), orderBy);
		return addRepo.findAll(pageRequest);
	}
	
	public List<Address> findAll(){
		return addRepo.findAll();
	}
	
}
