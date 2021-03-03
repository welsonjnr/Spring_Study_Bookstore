/*
package com.estudospring.livraria.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.UserLogin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		UserLogin logado = (UserLogin) authentication.getPrincipal();
		
		Date hoje = new Date();
		
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		//Método para a geração do token
		
				return Jwts.builder()
				//Qual API que está gerando esse token
				.setIssuer("API de Gerenciamento de Biblioteca")
				//Usúario autenticado que pertence o token
				.setSubject(logado.getId().toString())
				//Data de Geração do Token, utiliza o sistema antigo de geração de datas
				.setIssuedAt(hoje)
				//Tempo de expiração do token
				.setExpiration(dataExpiracao)
				//Pela documentação o token precisa ser criptografado, passando o algoritmo de criptografia e a senha da aplicação
				.signWith(SignatureAlgorithm.HS256, secret)
				//Compactar e transformar em uma string
				.compact()
				;
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		//Pegar o login do usuário pelo token
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
*/