package com.estudospring.livraria.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.dto.ClientDTO;
import com.estudospring.livraria.dto.ClientNewDTO;
import com.estudospring.livraria.services.ClientService;

@RestController
@RequestMapping(value = "/library/clients")
public class ClientResource {

	@Autowired
	private ClientService clientServ;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> find (@PathVariable Integer id){
		Client obj = clientServ.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/findClientName/{nameClient}")
	public ResponseEntity<List<Client>> findByNameClient(@PathVariable String nameClient){
		List<Client> obj = new ArrayList<Client>();
		if(nameClient.length() <= 3) {
			obj = clientServ.findByNameClient(nameClient);
		}
		else {
		String param = nameClient.replace(nameClient.substring(0, 1), nameClient.substring(0, 1).toUpperCase());
		    obj = clientServ.findByNameClient(param);
		}
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> findByFiltro(@RequestParam (value = "name", required = false) String name,
												   @RequestParam (value = "cpf", required = false) String cpf){
		Client filtro = new Client();
		filtro.setCpf(cpf);
		filtro.setName(name);
		List<Client> clients = clientServ.findByFiltro(filtro);
		return ResponseEntity.ok(clients);
	}
	
	/*
	 * Este método vai ser requisitado para o POST(Registro de algo no banco de dados)
	 * Vai pegar uma ResponseEntity vazia e vai requisitar um clientNewDto
	 * Então vou pegar um Client transforma-lo em DTO(Ou sejá as informações que quero que sejam passadas)
	 * E inseri-lo o obj no banco de dados
	 * E Construir uma URI com o objeto construido com um novo id
	 * E então retornar uma uri com a responseEntity Construída
	 */
	
	@PostMapping
	public ResponseEntity<ClientNewDTO> insert(@Valid @RequestBody ClientNewDTO clientObjDto){
		Client obj = clientServ.fromDTO(clientObjDto);
		obj = clientServ.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ClientDTO> update(@Valid @PathVariable Integer id, @RequestBody ClientDTO clientUpdate){
		Client obj = clientServ.fromDTO(clientUpdate);
		obj.setId(id);
		obj = clientServ.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/all")
	public ResponseEntity<List<ClientDTO>> findAll(){
		List<Client> list = clientServ.findAll();
		/*
		 * Método muito interessante. Ele vai pegar uma lista de Cliente-> Transformar
		 * em stream. Vai mapear cada stream e em cada uma ele vai transformar em apenas
		 * o objeto ClienteDTO. Depois vai coletar elas e transformar novamente em
		 * lista. GENIUS!!
		 */
		List<ClientDTO> listDto = list.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
			
		}
		
		@DeleteMapping(value="/{id}")
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			clientServ.delete(id);
			return ResponseEntity.noContent().build();
	}
		/*
		 * Para encontrar esse objeto no banco de dados é necessário que se coloque
		 * "value" Que no caso seria "page". Page é um tipo especial para pegar o
		 * quantidade de objetos por páginas. Para não sobrecarregar o sistema.
		 */
		@GetMapping(value="/find/page")
		public ResponseEntity<Page<Client>> findPage(
				// RequestParam: Serve para colocar as informações como padrão. Caso não
				// informada pelo usuário
				@RequestParam(value="page", defaultValue="0") Integer page,
				@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
				@RequestParam(value="orderBy", defaultValue= "name") String orderBy,
				@RequestParam(value="direction",defaultValue ="ASC" ) String direction){
			Page<Client> list = clientServ.findPage(page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
		}
	
}
