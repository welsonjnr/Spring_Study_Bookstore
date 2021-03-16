package com.estudospring.livraria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.estudospring.livraria.services.DBService;


@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	/*Esse método vai pegar o valor da chave de modelo de instanciação da JPA
	no mysql e dependendo do valor("create"), não vai instnciar
	Para que não saia os dados repetidos
	*/
	
	//Esse value está pegando o valor desse campo no application-dev.properties
	//e armazenando na variável String strategy
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() {
		if(!"create".equals(strategy)) {
			return false;
		}
		dbService.instatiateTestDatabase();
		return true;
	}		
}

