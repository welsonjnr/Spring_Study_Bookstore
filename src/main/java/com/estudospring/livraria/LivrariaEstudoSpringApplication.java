package com.estudospring.livraria;

import java.time.LocalDate;
import java.time.Month;
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
import com.estudospring.livraria.domain.Loan;
import com.estudospring.livraria.domain.enums.BookStatus;
import com.estudospring.livraria.domain.enums.LoanStatus;
import com.estudospring.livraria.domain.enums.UserType;
import com.estudospring.livraria.repositories.AddressRepository;
import com.estudospring.livraria.repositories.BookRepository;
import com.estudospring.livraria.repositories.CategoryRepository;
import com.estudospring.livraria.repositories.CityRepository;
import com.estudospring.livraria.repositories.ClientRepository;
import com.estudospring.livraria.repositories.LoanRepository;

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
	@Autowired
	private LoanRepository loanRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaEstudoSpringApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Literatura");
		Category cat2 = new Category(null, "Financias");
		Category cat3 = new Category(null, "Esportes");
		
		Book bk1 = new Book(null, "ABCD", "João", 3, BookStatus.AVAILABLE, 2, cat2);
		Book bk2 = new Book(null, "Fique Rico", "Tio Huli", 2, BookStatus.BORROWED, 2,cat1);
		Book bk3 = new Book(null, "No céu tem Pão?", "Desbersval", 1, BookStatus.SINGLE, 1,cat2);
		Book bk4 = new Book(null, "asdfsadfsf", "asdf", 3, BookStatus.AVAILABLE, 2, cat3);
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		bookRepository.saveAll(Arrays.asList(bk1, bk2, bk3, bk4));
		
		City cit1 = new City(null, "Goiás", "15675000", "GO");
		City cit2 = new City(null, "Palmeiras", "76148000", "SP");
		City cit3 = new City(null, "Manaus", "35265123", "AM");
		
		cityRepository.saveAll(Arrays.asList(cit1, cit2, cit3));
		
		Client cli1 = new Client(null, "João", "83871908029", "ADM", "UNIMB", "joao@gmail.com", 3, UserType.STUDENT);
		Client cli2 = new Client(null, "Maria", "47895603094", "Farmácia", "PUC", "maria@gmail.com", 1, UserType.STUDENT);
		Client cli3 = new Client(null, "Marcos", "17981772001", "Advocacia", "Universidade de Lisboa", "marcos@gmail.com", 2, UserType.TEACHER);
		Client cli4 = new Client(null, "João", "83871908029", "ADM", "UNIMB", "joao@gmail.com", 3, UserType.STUDENT);
		
		Address adr1 = new Address(null, "JK", 15, "Centro", cli1, cit1);
		Address adr2 = new Address(null, "Campos Novos", 243, "São Bernado", cli2, cit2);
		Address adr3 = new Address(null, "Saião", 23, "Calibri", cli3, cit3);
		
		cli1.getPhones().addAll(Arrays.asList("927351525", "98502135"));
		cli2.getPhones().addAll(Arrays.asList("987652535"));
		cli3.getPhones().addAll(Arrays.asList("925314576", "987642534"));
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
		
		addressRepository.saveAll(Arrays.asList(adr1, adr2, adr3));
	
		Loan loan1 = new Loan (null, LocalDate.of(2019, Month.MARCH, 5) , LoanStatus.BORROWED, bk1, cli1);
		Loan loan2 = new Loan (null, LocalDate.of(2019, Month.APRIL, 10), LoanStatus.LATE, bk2, cli1);
		Loan loan3 = new Loan (null, LocalDate.of(2019, Month.JULY, 2), LoanStatus.CANCELED, bk3, cli3);
		
		cli1.getLoans().addAll(Arrays.asList(loan1, loan2));
		cli3.getLoans().addAll(Arrays.asList(loan3));
		
		loanRepository.saveAll(Arrays.asList(loan1, loan2, loan3));
	}
}