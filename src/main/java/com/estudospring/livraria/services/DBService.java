/*package com.estudospring.livraria.services;

import java.util.Arrays;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.domain.Loan;
import com.estudospring.livraria.domain.UserLogin;
import com.estudospring.livraria.domain.enums.BookStatus;
import com.estudospring.livraria.domain.enums.LoanStatus;
import com.estudospring.livraria.domain.enums.StatusClient;
import com.estudospring.livraria.repositories.BookRepository;
import com.estudospring.livraria.repositories.ClientRepository;
import com.estudospring.livraria.repositories.LoanRepository;
import com.estudospring.livraria.repositories.UserLoginRepository;

@Service
public class DBService {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private LoanRepository loanRepository;
	@Autowired
	private UserLoginRepository userLoginRepo;
	
	public void instatiateTestDatabase() {
		
		UserLogin userLogin = new UserLogin(null, "joão", "a@gmail.com", "987654");
		UserLogin userLogin1 = new UserLogin(null, "Fernando", "j@gmail.com", "123456");
		
		userLoginRepo.saveAll(Arrays.asList(userLogin, userLogin1));
		
		
		Book bk1 = new Book(null, "ABCD", "João", "1° Edição SP", BookStatus.DISPONIVEL, 2, "Financias");
		Book bk2 = new Book(null, "Fique Rico", "Tio Huli", "Edição BR Limitada",BookStatus.EMPRESTADO, 2, "Autoajuda");
		Book bk3 = new Book(null, "No céu tem Pão?", "Desbersval", "2° GO",BookStatus.UNICO, 1, "Literatura");
		Book bk4 = new Book(null, "asdfsadfsf", "asdf", "Limited 3°",BookStatus.DISPONIVEL, 2, "Sim literatura");
		
		bookRepository.saveAll(Arrays.asList(bk1, bk2, bk3, bk4));
		
		Client cli1 = new Client(null, "João C", "83871908029", "ADM", "UNIMB", "joao@gmail.com", 3, StatusClient.PENDENTE);
		Client cli2 = new Client(null, "Maria B", "47895603094", "Farmácia", "PUC", "maria@gmail.com", 1, StatusClient.DISPONIVEL);
		Client cli3 = new Client(null, "Marcos A", "17981772001", "Advocacia", "Universidade de Lisboa", "marcos@gmail.com", 2, StatusClient.PENDENTE);
		Client cli4 = new Client(null, "João S", "83871908029", "ADM", "UNIMB", "joao@gmail.com", 3, StatusClient.DISPONIVEL);
		
	
		clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4));
		
	
		Loan loan1 = new Loan (null,  LocalDate.now().withDayOfMonth(2).toString(), LocalDate.now().withDayOfMonth(2).plusDays(8).toString(), LoanStatus.OK, bk1, cli1);
		Loan loan2 = new Loan (null, LocalDate.now().withDayOfMonth(2).toString(), LocalDate.now().withDayOfMonth(4).plusDays(7).toString(),LoanStatus.CANCELADO, bk2, cli1);
		Loan loan3 = new Loan (null, LocalDate.now().withDayOfMonth(2).toString(), LocalDate.now().withDayOfMonth(8).plusDays(3).toString(),LoanStatus.RENOVADO, bk3, cli3);
		
		cli1.getLoans().addAll(Arrays.asList(loan1, loan2));
		cli3.getLoans().addAll(Arrays.asList(loan3));
		
		loanRepository.saveAll(Arrays.asList(loan1, loan2, loan3));
	}
	
}
*/