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
import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.domain.enums.UserType;
import com.estudospring.livraria.repositories.AddressRepository;
import com.estudospring.livraria.repositories.BookRepository;
import com.estudospring.livraria.repositories.CategoryRepository;
import com.estudospring.livraria.repositories.CityRepository;
import com.estudospring.livraria.repositories.ClientRepository;

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
	@Autowired
	private ClientRepository clientRepository;
	
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
		
		cityRepository.saveAll(Arrays.asList(cit1, cit2, cit3));
		
		Client cli1 = new Client(null, "João", "12365498700", "ADM", "UNIMB", "joao@gmail.com", 3, UserType.STUDENT);
		Client cli2 = new Client(null, "Maria", "15975364825", "Farmácia", "PUC", "maria@gmail.com", 1, UserType.STUDENT);
		Client cli3 = new Client(null, "Marcos", "02153425899", "Advocacia", "Universidade de Lisboa", "marcos@gmail.com", 2, UserType.TEACHER);
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		
		cli1.getPhones().addAll(Arrays.asList("927351525", "98502135"));
		cli2.getPhones().addAll(Arrays.asList("987652535"));
		cli3.getPhones().addAll(Arrays.asList("925314576", "987642534"));
		
		Address adr1 = new Address(null, "JK", 15, "Centro", cit1, cli1);
		Address adr2 = new Address(null, "Campos Novos", 243, "São Bernado", cit2, cli2);
		Address adr3 = new Address(null, "Saião", 23, "Calibri", cit3, cli3);
		
		cit1.getAddress().addAll(Arrays.asList(adr1, adr2));
		cit2.getAddress().addAll(Arrays.asList(adr3));
		
		cli1.getAdress().addAll(Arrays.asList(adr1));
		cli2.getAdress().addAll(Arrays.asList(adr2));
		cli3.getAdress().addAll(Arrays.asList(adr3));
		
		addressRepository.saveAll(Arrays.asList(adr1, adr2, adr3));
		
	}
}

