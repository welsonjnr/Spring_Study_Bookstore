/*
package com.estudospring.livraria.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.UserLogin;
import com.estudospring.livraria.repositories.UserLoginRepository;

//Mostrando que essa service tem a lógica de autenticação
@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UserLoginRepository userLoginRepo;
	
	//Método usado para a autenticação do usuário, passando o email e senha do usuário
	//Procurando o usuário pelo email e pegando as informações
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserLogin> usuario = userLoginRepo.findByEmail(username);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Dados Inválidos!!");
	}
}
*/