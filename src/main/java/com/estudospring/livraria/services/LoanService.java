package com.estudospring.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.domain.Loan;
import com.estudospring.livraria.domain.enums.BookStatus;
import com.estudospring.livraria.domain.enums.LoanStatus;
import com.estudospring.livraria.domain.enums.StatusClient;
import com.estudospring.livraria.repositories.LoanRepository;
import com.estudospring.livraria.services.exceptions.BookUnavailableForLoan;
import com.estudospring.livraria.services.exceptions.CustomerWithOpenLoan;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class LoanService {

	@Autowired
	private LoanRepository repoLoan;

	@Autowired
	private ClientService servCli;
	
	@Autowired 
	private BookService servBook;
	
	public Loan find(Integer id) {
		Optional<Loan> obj = repoLoan.findById(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Id: " + id + ", Tipo: " + Loan.class.getName());
		}
		return obj.orElseThrow(null);
	}
	

	public Loan insert(Loan loan) {
		loan.setId(null);
		loan.setClient(validationClient(loan.getClient().getId()));
		
		Client cli = servCli.find(loan.getClient().getId());
		cli.setStatusClient(StatusClient.PENDING);
		servCli.update(cli);
		
		loan.setBook(validationBook(loan.getBook().getId()));
		Book book = servBook.find(loan.getClient().getId());
		book.setAmount(book.getAmount() -1);
		if(book.getAmount() == 1) {
			book.setBookStatus(BookStatus.SINGLE);
		}
		servBook.update(book);
		
		return repoLoan.save(loan);
		
	}
	
	public Loan renewal(Loan loan) {
		Loan loanRenewal = find(loan.getId());
		loanRenewal.setLoanStatus(LoanStatus.RENOVATED);
		return repoLoan.save(loanRenewal);
	}
	
	public Loan returned(Loan loan) {
		Loan loanReturned = find(loan.getId());
		loanReturned.setLoanStatus(LoanStatus.RETURNED);
		Book book = servBook.find(loan.getBook().getId());
		book.setAmount(book.getAmount() + 1);
		if(book.getAmount() > 1) {
			book.setBookStatus(BookStatus.AVAILABLE);
		}
		servBook.update(book);
		
		Client client = servCli.find(loan.getClient().getId());
		client.setStatusClient(StatusClient.RELEASED);
		servCli.update(client);

		return repoLoan.save(loanReturned);
	}
	
	
	public void delete(Integer id) {
		find(id);
		try {
			repoLoan.deleteById(id);
		}
		catch(DataIntegrityViolationException e){
			throw new DataIntegrityViolationException("Não foi possível excluir, poís há entidades relacionadas!");
		}
	}
	
	public List<Loan> findAll() {
		return repoLoan.findAll();
	}
	
	public Page<Loan> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repoLoan.findAll(pageRequest);
	}

	public Client validationClient(Integer id) {
		Client cli = servCli.find(id);
		if(cli.getStatusClient() == StatusClient.PENDING) {
			throw new CustomerWithOpenLoan("O usuário está com um empréstimo pendente!");
		}
		return cli;
	}
	
	public Book validationBook(Integer id) {
		Book book = servBook.find(id);
		
		if(book.getAmount() <= 1) {
			throw new BookUnavailableForLoan("O Livro não pode ser Emprestado");
		}
		
		if(book.getBookStatus() == BookStatus.SINGLE || book.getBookStatus() == BookStatus.BORROWED) {
			throw new BookUnavailableForLoan("O Livro não pode ser Emprestado");
		}
		
		return book;
		 	
	}
	
}