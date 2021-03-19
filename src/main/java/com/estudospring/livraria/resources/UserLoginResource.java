package com.estudospring.livraria.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudospring.livraria.domain.UserLogin;
import com.estudospring.livraria.dto.UserLoginDTO;
import com.estudospring.livraria.services.UserLoginService;
import com.estudospring.livraria.services.exceptions.ErrorAuthentication;


@RestController
@RequestMapping(value= "/library/user")
public class UserLoginResource {

	@Autowired
	private UserLoginService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserLogin> findByEmail(@PathVariable Long id){
		UserLogin obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<UserLogin> insert(@Valid @RequestBody UserLoginDTO user){
		UserLogin userLogin= service.fromDTO(user);
		userLogin = service.insert(userLogin);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userLogin.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> autenticar(@RequestBody UserLoginDTO userDTO){
		try {
			UserLogin userAuth = service.autenticarUsuario(userDTO.getEmail(), userDTO.getSenha());
			return ResponseEntity.ok(userAuth);
		}catch(ErrorAuthentication e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity<List<UserLogin>> findAll(){
		List<UserLogin> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PutMapping(value="/{id}")
	@Transactional
	public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody UserLoginDTO userDTO){
		UserLogin user = service.fromDTO(userDTO);
		user.setId(id);
		user  = service.update(user);
	
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
