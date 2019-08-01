package com.estudospring.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Category;
import com.estudospring.livraria.dto.CategoryDTO;
import com.estudospring.livraria.repositories.CategoryRepository;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName());
		}

		return obj.orElse(null);
	}

	public Category insert(Category obj) {
		// Para garantir que não colocar no lugar de outra categoria que tenha o mesmo
		// id
		obj.setId(null);
		return repo.save(obj);
	}

	public Category update(Category obj) {
		// Vou criar um novo obj que vai encrontar o meu objeto que quero editar pelo id
		Category newObj = find(obj.getId());
		// Método a ser implementado no Resource
		updateData(newObj, obj);
		// Vou retornar o novo objeto salvo e editado
		return repo.save(newObj);
	}

	public void delete(Integer idObj) {
		// Encontra o objeto com o id passado como parâmetro
		find(idObj);
		try {
			repo.deleteById(idObj);
		}
		// O try/catch é necessáro pois se ouver alguma outra classe relacionada com
		// essa classe atual é necessário outro tratamento
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Não foi possível excluir, poís, possuem outras categorias relacionada a essa!");
		}

	}

	//O comando repo.findAll() foi utilizado da própria IDE. Não sendo necessária implementa-lá
	public List<Category> findAll(){
		return repo.findAll();
	}
	
	//Método para transforma uma categoria normal em categoriaDTO
	public Category fromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}
	
	/* Tu vai fazer esse método para que pegue as informações da categoria que são
	para serem mudadas e mudar e retornar para o método update
	*/
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
