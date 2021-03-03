/*
package com.estudospring.livraria.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.estudospring.livraria.repositories.UserLoginRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserLoginRepository userRepo;
	
	//Injetando a dependência para a procura e autenticação do usuário no banco de dados
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
	//Configurações de Autenticação -- Controle de acesso
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//Método demonstrando qual classe que tem a lógica de autenticação
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//Configurações de Autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET,"/books").permitAll()
		.antMatchers(HttpMethod.GET, "/books/*").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.anyRequest().authenticated()
		.and().cors()
		.and().csrf().disable()
		//Avisando para o Spring Security que nossa política de criação será STATELESS e não SESSION
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, userRepo), UsernamePasswordAuthenticationFilter.class)
		;
	}
	
	//Configurações de Recursos Estáticos(js, css, imagens)
	@Override
	public void configure(WebSecurity web) throws Exception {

	} 
}
*/