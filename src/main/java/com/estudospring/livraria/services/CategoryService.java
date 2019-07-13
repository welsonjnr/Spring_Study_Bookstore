package com.estudospring.livraria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Category;
import com.estudospring.livraria.repositories.CategoryRepository;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);

		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Category.class.getName());
		}
		
		return obj.orElse(null);
		}	
}
