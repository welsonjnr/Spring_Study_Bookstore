package com.estudospring.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudospring.livraria.domain.City;
import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.domain.enums.StatusClient;
import com.estudospring.livraria.dto.ClientDTO;
import com.estudospring.livraria.dto.ClientNewDTO;
import com.estudospring.livraria.repositories.ClientRepository;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repoCli;

	
	public Client find(Integer id) {
		Optional<Client> obj = repoCli.findById(id);

		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Id: " + id + ", Tipo: " + Client.class.getName());
		}
		return obj.orElse(null);
	}

	public List<Client> findByNameClient(String nameClient) {
		List<Client> obj = repoCli.findByNameContaining(nameClient);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Nome do Cliente: " + nameClient + ", Tipo: " + Client.class.getName());
		}
			return obj;
		}
	
	public List<Client> findByCpfClient(String nameClient) {
		List<Client> obj = repoCli.findByNameContaining(nameClient);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Nome do Cliente: " + nameClient + ", Tipo: " + Client.class.getName());
		}
			return obj;
		}
	
	/*
	 * Estou pegando um objeto ClientNewDto e colocando as informações dentro dele
	 * Como a implementação vai ser feita depois do objeto for instaciado, não vou
	 * precisar coloca-lo aqui. Mas, não pesquisa do JSON Aqui também tem de fazer
	 * as implementações e ligações padrões, como relacionar o address em client
	 */
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), null, objDto.getCourse(), objDto.getInstitution(), objDto.getEmail(), objDto.getPeriod(), null);
	}

	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getCpf(), objDto.getCourse(), objDto.getInstitution(), objDto.getEmail(), objDto.getPeriod(), StatusClient.toEnum(objDto.getType()));
	
		return cli;
	}
	
	/*
	 * Estou pegando um objeto do tipo cliente e colocando o id dele como null. E
	 * logo após estou savando ele no repositório E pegando o seu endereço e
	 * retornando o obj pronto
	 */
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj.setStatus(StatusClient.DISPONIVEL);
		obj = repoCli.save(obj);
		return obj;

	}

	/*
	 * Método para a atualização de cliente
	 * Estou recebendo um objeto client
	 * E criando um novo com o id do objeto que recebi
	 * Estou o usando o método updateData para colocar as informações de maneira correta 
	 * E retornando o novo objeto salvo
	 */
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repoCli.save(newObj);
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
		
		if(obj.getCourse() != null) {
			newObj.setCourse(obj.getCourse());
		}
		if(obj.getInstitution() != null) {
			newObj.setInstitution(obj.getInstitution());
		}
		if(obj.getPeriod() != null) {
			newObj.setPeriod(obj.getPeriod());
		}
	}

	public List<Client> findAll() {
		return repoCli.findAll();
	}
	
	public List<Client> findByFiltro(Client filtro){
		Example example = Example.of(filtro, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		
		return repoCli.findAll(example);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repoCli.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não foi possível excluir, poís há entidades relacionadas!");
		}
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repoCli.findAll(pageRequest);
	}
	
}
