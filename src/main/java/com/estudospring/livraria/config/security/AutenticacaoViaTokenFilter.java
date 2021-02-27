package com.estudospring.livraria.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.estudospring.livraria.domain.UserLogin;
import com.estudospring.livraria.repositories.UserLoginRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserLoginRepository userRepo;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UserLoginRepository userRepo) {
		this.tokenService = tokenService;
		this.userRepo = userRepo;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		boolean valido = tokenService.isTokenValido(token);
		
		if(valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}
	//Spring não faça a lógica toda novamente só autentique esse token e veja se está valendo ainda
	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		UserLogin userLogin = userRepo.findById(idUsuario).get();
		//Passar a variável que contenha as informações do usuário
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userLogin, null, userLogin.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

	//Recuperar o token do usuário que já usou uma vez
	private String recuperarToken(HttpServletRequest request) {
		//Pegar a informação do Header Authorization(Token)
		String token = request.getHeader("Authorization");
		//Verificar se o cabeçalho da requisisção está de acordo com a configuração
		if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		return token.substring(7, token.length());
	}
}