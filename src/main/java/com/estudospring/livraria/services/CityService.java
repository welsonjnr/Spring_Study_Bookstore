package com.estudospring.livraria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.City;
import com.estudospring.livraria.repositories.CityRepository;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository repoCity;
	
	public City find(Integer id) {
		Optional<City> obj = repoCity.findById(id);
		
		if(obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + City.class.getName());
		}
			return obj.orElse(null);
	}
	
	public City insert(City city) {
		city.setId(null);
		return repoCity.save(city);
	}
	
	public City update(City city) {
		Optional<City> obj = repoCity.findById(city.getId());
		City cidade = obj.get();
		return repoCity.save(cidade);
	}
	
	public void remove(Integer id) {
		find(id);
		try {
			repoCity.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Não foi possível excluir, poís, possuem outras categorias relacionada a essa!");
		}
		
	}
	
	public Page<City> findPage(Integer pages, Integer linePerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(pages, linePerPage, Direction.valueOf(direction), orderBy);
		return repoCity.findAll(pageRequest);
	}
	
}
