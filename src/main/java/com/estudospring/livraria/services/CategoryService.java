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
	
	public Category insert (Category obj) {
		//Para garantir que não colocar no lugar de outra categoria que tenha o mesmo id
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		//Vou criar um novo obj que vai encrontar o meu objeto que quero editar pelo id
		Category newObj = find(obj.getId());
		//Método a ser implementado no Resource
		updateData(newObj, obj);
		//Vou retornar o novo objeto salvo e editado
		return repo.save(newObj);
	}

	
	//Tu vai fazer esse método para que pegue as informações da categoria que são possiveis de serem mudadas e mudar e retornar mata o método update
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
