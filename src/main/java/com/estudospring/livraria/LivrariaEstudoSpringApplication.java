package com.estudospring.livraria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudospring.livraria.domain.Category;
import com.estudospring.livraria.repositories.CategoryRepository;

@SpringBootApplication
public class LivrariaEstudoSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaEstudoSpringApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Literatura");
		Category cat2 = new Category(null, "Financias");
		Category cat3 = new Category(null, "Esportes");
				
	}
}
