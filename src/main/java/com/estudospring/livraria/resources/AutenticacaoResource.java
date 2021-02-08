/*
package com.estudospring.livraria.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudospring.livraria.config.security.TokenService;
import com.estudospring.livraria.dto.LoginForm;
import com.estudospring.livraria.dto.TokenDto;

@RestController
@RequestMapping("/auth")
public class AutenticacaoResource {

	//Necessária para a autenticação de maneira manual, a gente programando a lógica dela
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	//O cliente vai enviar os dados no LoginForm para a autenticacão no banco de dados
	//No caso esse aqui é o resource que vai ser chamado para a autenticação e o service vai fazer o resto atrás
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		//Para Fazer a autenticação eu preciso do método authenticate do AuthenticationManager
		//Porem, esse método aceita um tipo específico de objeto que é o UserPasswordAuthenticationToken
		// E criei um método na própria classe Login para fazer essa conversão e usar no método authenticate
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			//geração do token
			String token = tokenService.gerarToken(authentication);
			//Mandar para o usuário o token dele e o tipo de requisições da proxima das proximas vezes
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		
		} 
		catch(AuthenticationException e) {
			return ResponseEntity.badRequest().build();	
		}
		
	}
	
}
*/