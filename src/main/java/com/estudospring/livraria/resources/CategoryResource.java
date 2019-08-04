/*
 * Importante!!! 
 * O usuário através da View vai entrar com a informação dos comandos que deseja executar
 * Logo após, a View vai mandar os comando para o REST que é esse aqui
 * E logo após vai para o Service 
 * BD
 * e retorna para o usuário
 */

package com.estudospring.livraria.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	/*
	 * ResponseEntity é utilizado para retorna um corpo completo em Http: Código de
	 * status, cabeçalho, e corpo. Por isso é utilizado para retornar totalmente uma
	 * requisição http da aplicação Sendo genérica o qual é possivel usar qualquert
	 * tipo de resposta
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Category obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	/*
	 * A criação da CategoriaDTO serve para que e inclua o que deva ser buscado do
	 * objeto no banco de dados
	 * 
	 * @Valid é utilizado que a aplicação valide antes que envie para o Banco de
	 * dados Esse método em resumo vai transformar um objeto Category em CategoryDTO
	 * E logo após vai pegar uma uri, com o caminho e construir um novo id e
	 * retornar em uma nova uri
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDto) {
		Category obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/*
	 * Estou pegando uma ResponseEntity vazia que vai receber uma CategoriaDTO e um
	 * id variável Então vou criar uma categoria obj e transforma-la em Dto Logo
	 * após, vou colocar o id E fazer a atualização com o método da classe
	 * CategoryService E construir uma ResponseEntity
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO objDto, @PathVariable Integer id) {
		Category obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	/*
	 * Pegando o id de um objeto e o colocando para ser deletado pelo método delete
	 * da classe Service
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> list = service.findAll();
		/*
		 * Método muito interessante. Ele vai pegar uma lista de Categoria-> Transformar
		 * em stream. Vai mapear cada stream e em cada uma ele vai transformar em apenas
		 * o objeto CategoriaDTO. Depois vai coletar elas e transformar novamente em
		 * lista. GENIUS!!
		 */
		List<CategoryDTO> listDto = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	/*
	 * Essa classe vai impedir o estouro da mémoria com pesquisas demais Então se o
	 * usuário não inserir o parâmetros de pesquisa vai ter o padrão por causa
	 * do @ResquestParm Vai transformar a categoria nas páginas criar o objDto e
	 * retornar a lista!
	 */
	public ResponseEntity<Page<CategoryDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer pages,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "value", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Category> list = service.findPage(pages, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listDto = list.map(obj -> new CategoryDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}

}
