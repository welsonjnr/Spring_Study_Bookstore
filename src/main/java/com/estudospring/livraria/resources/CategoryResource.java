package com.estudospring.livraria.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudospring.livraria.domain.Category;
import com.estudospring.livraria.dto.CategoryDTO;
import com.estudospring.livraria.services.CategoryService;

@RestController
@RequestMapping(value = "/categorys")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	//ResponseEntity é utilizado para retorna um corpo completo em Http: Código de status, cabeçalho, e corpo. Por isso é utilizado para retornar totalmente uma requisição http da aplicação
	//Sendo genérica o qual é possivel usar qualquert tipo de resposta
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Category obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*A criação da CategoriaDTO serve para que e inclua o que deva ser buscado do objeto no banco de dados
	@Valid é utilizado que a aplicação valide antes que envie para o Banco de dados
	Esse método em resumo vai transformar um objeto Category em CategoryDTO 
	E logo após vai pegar uma uri, com o caminho e construir um novo id e retornar em uma nova uri
	*/
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDto){
		Category obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}

}
