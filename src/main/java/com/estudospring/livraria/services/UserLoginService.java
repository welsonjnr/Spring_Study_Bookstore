package com.estudospring.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudospring.livraria.domain.UserLogin;
import com.estudospring.livraria.dto.UserLoginDTO;
import com.estudospring.livraria.repositories.UserLoginRepository;
import com.estudospring.livraria.services.exceptions.EmailUserExists;
import com.estudospring.livraria.services.exceptions.ErrorAuthentication;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class UserLoginService {

	@Autowired
	private UserLoginRepository userLoginRepo;
	
	public UserLogin find(Long idUserLogin) {
		Optional<UserLogin> userLogin = userLoginRepo.findById(idUserLogin);

		if (userLogin == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + idUserLogin + ", Tipo: " + UserLogin.class.getName());
		}
		return userLogin.orElse(null);
	}
	
	@Transactional
	public UserLogin insert(UserLogin userLogin) {
		Boolean validacao = validacaoEmail(userLogin.getEmail());
		if(validacao) {
			throw new EmailUserExists("O email já consta na base de dados!");
		}else {
				
		return userLoginRepo.save(userLogin);
		}
	}
	
	public UserLogin update(UserLogin userLogin) {
		UserLogin userLoginUpdate = find(userLogin.getId());
		updateData(userLoginUpdate, userLogin);
		return userLoginRepo.save(userLoginUpdate);
	}
	
	public void delete(Long idUserLogin) {
		find(idUserLogin);
		try {
			userLoginRepo.deleteById(idUserLogin);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Não foi possível excluir o usuário!");
		}
	}

	public List<UserLogin> findAll() {
		return userLoginRepo.findAll();
	}
	
	public Boolean validacaoEmail(String email) {
		Boolean validacao = userLoginRepo.existsByEmail(email);
		return validacao;
	}
	
	public UserLogin autenticarUsuario(String email, String senha) {
		Optional<UserLogin> usuario = userLoginRepo.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErrorAuthentication("Usuário não encontrado para o email informado.");
		}
		
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErrorAuthentication("Senha inválida.");
		}
		
		return usuario.get();
	}
	
	public UserLogin fromDTO(UserLoginDTO objDto) {
		return new UserLogin(null, objDto.getNome(), objDto.getEmail(), objDto.getSenha());
	}
	
	private void updateData(UserLogin userLoginUpdate, UserLogin userLogin) {
		if (userLogin.getEmail() != null) {
			userLoginUpdate.setEmail(userLogin.getEmail());
		}
		if (userLogin.getNome() != null) {
			userLoginUpdate.setNome(userLogin.getNome());
		}
		if (userLogin.getSenha() != null) {
			userLoginUpdate.setSenha(userLogin.getSenha());
				}
			}
		}
