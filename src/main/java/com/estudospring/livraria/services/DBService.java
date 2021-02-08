package com.estudospring.livraria.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Address;
import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.domain.Category;
import com.estudospring.livraria.domain.City;
import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.domain.Loan;
//import com.estudospring.livraria.domain.UserLogin;
import com.estudospring.livraria.domain.enums.BookStatus;
import com.estudospring.livraria.domain.enums.LoanStatus;
import com.estudospring.livraria.domain.enums.StatusClient;
import com.estudospring.livraria.repositories.AddressRepository;
import com.estudospring.livraria.repositories.BookRepository;
import com.estudospring.livraria.repositories.CategoryRepository;
import com.estudospring.livraria.repositories.CityRepository;
import com.estudospring.livraria.repositories.ClientRepository;
import com.estudospring.livraria.repositories.LoanRepository;
//import com.estudospring.livraria.repositories.UserLoginRepository;

@Service
public class DBService {
	
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
	
	//@Autowired
//	private UserLoginRepository userLoginRepo;
	
	public void instatiateTestDatabase() {
		
//		UserLogin userLogin = new UserLogin(null, "joão", "a@gmail.com", "$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq");
		
	//	userLoginRepo.save(userLogin);
		
		Category cat1 = new Category(null, "Literatura");
		Category cat2 = new Category(null, "Financias");
		Category cat3 = new Category(null, "Esportes");
		
		Book bk1 = new Book(null, "ABCD", "João", "1° Edição SP", BookStatus.DISPONIVEL, 2, cat2);
		Book bk2 = new Book(null, "Fique Rico", "Tio Huli", "Edição BR Limitada",BookStatus.EMPRESTADO, 2,cat1);
		Book bk3 = new Book(null, "No céu tem Pão?", "Desbersval", "2° GO",BookStatus.UNICO, 1,cat2);
		Book bk4 = new Book(null, "asdfsadfsf", "asdf", "Limited 3°",BookStatus.DISPONIVEL, 2, cat3);
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		bookRepository.saveAll(Arrays.asList(bk1, bk2, bk3, bk4));
		
		City cit1 = new City(null, "Goiás", "15675000", "GO");
		City cit2 = new City(null, "Palmeiras", "76148000", "SP");
		City cit3 = new City(null, "Manaus", "35265123", "AM");
		
		cityRepository.saveAll(Arrays.asList(cit1, cit2, cit3));
		
		Client cli1 = new Client(null, "João", "83871908029", "ADM", "UNIMB", "joao@gmail.com", 3, StatusClient.PENDENTE);
		Client cli2 = new Client(null, "Maria", "47895603094", "Farmácia", "PUC", "maria@gmail.com", 1, StatusClient.DISPONIVEL);
		Client cli3 = new Client(null, "Marcos", "17981772001", "Advocacia", "Universidade de Lisboa", "marcos@gmail.com", 2, StatusClient.PENDENTE);
		Client cli4 = new Client(null, "João", "83871908029", "ADM", "UNIMB", "joao@gmail.com", 3, StatusClient.DISPONIVEL);
		
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
