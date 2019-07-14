package com.estudospring.livraria;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudospring.livraria.domain.Address;
import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.domain.Category;
import com.estudospring.livraria.domain.City;
import com.estudospring.livraria.repositories.AddressRepository;
import com.estudospring.livraria.repositories.BookRepository;
import com.estudospring.livraria.repositories.CategoryRepository;
import com.estudospring.livraria.repositories.CityRepository;

@SpringBootApplication
public class LivrariaEstudoSpringApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaEstudoSpringApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Literatura");
		Category cat2 = new Category(null, "Financias");
		Category cat3 = new Category(null, "Esportes");
		
		Book bk1 = new Book(null, "ABCD", "João", 3);
		Book bk2 = new Book(null, "Fique Rico", "Tio Huli", 2);
		Book bk3 = new Book(null, "No céu tem Pão?", "Desbersval", 1);
		
		cat1.getBook().addAll(Arrays.asList(bk1, bk2, bk3));
		cat2.getBook().addAll(Arrays.asList(bk2));
		cat3.getBook().addAll(Arrays.asList(bk3));
		
		bk1.getCategory().addAll(Arrays.asList(cat1));
		bk2.getCategory().addAll(Arrays.asList(cat1, cat2));
		bk3.getCategory().addAll(Arrays.asList(cat1, cat3));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		bookRepository.saveAll(Arrays.asList(bk1, bk2, bk3));
		
		City cit1 = new City(null, "Goiás", "15675000", "GO");
		City cit2 = new City(null, "Palmeiras", "76148000", "SP");
		City cit3 = new City(null, "Manaus", "35265123", "AM");

		Address adr1 = new Address(null, "JK", 15, "Centro", cit1);
		Address adr2 = new Address(null, "Campos Novos", 243, "São Bernado", cit2);
		Address adr3 = new Address(null, "Saião", 23, "Calibri", cit3);
		
		cit1.getAddress().addAll(Arrays.asList(adr1));
		cit2.getAddress().addAll(Arrays.asList(adr2));
		cit3.getAddress().addAll(Arrays.asList(adr3));
		

		cityRepository.saveAll(Arrays.asList(cit1, cit2, cit3));
		addressRepository.saveAll(Arrays.asList(adr1, adr2, adr3));
		
		
	}
}

