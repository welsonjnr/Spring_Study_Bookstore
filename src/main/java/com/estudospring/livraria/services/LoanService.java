package com.estudospring.livraria.services;

import java.util.List;
import java.util.Optional;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
import com.estudospring.livraria.dto.LoanNewDTO;
import com.estudospring.livraria.repositories.LoanRepository;
import com.estudospring.livraria.services.exceptions.BookIsNotAvailableForRenewal;
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
	
	DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
	
	public Loan find(Integer id) {
		Optional<Loan> obj = repoLoan.findById(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Id: " + id + ", Tipo: " + Loan.class.getName());
		}
		return obj.orElseThrow(null);
	}
	
	public List<Loan> findLoanByNameClient(String filtro) {
		List<Loan> obj = repoLoan.findByClientNameContaining(filtro);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Nome do Cliente: " + filtro + ", Tipo: " + Client.class.getName());
		}
			return obj;
	}
	
	public List<Loan> findLoanByCpfClient(String filtro) {
		List<Loan> obj = repoLoan.findByClientCpfContaining(filtro);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! CPF do Cliente: " + filtro + ", Tipo: " + Client.class.getName());
		}
			return obj;
	}
	
	public List<Loan> findLoanByNameBook(String filtro) {
		List<Loan> obj = repoLoan.findByBookNameContaining(filtro);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Nome do Livro: " + filtro + ", Tipo: " + Book.class.getName());
		}
			return obj;
	}
	
	public List<Loan> findLoanByAuthorBook(String filtro) {
		List<Loan> obj = repoLoan.findByBookAuthorContaining(filtro);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Autor do Livro: " + filtro + ", Tipo: " + Book.class.getName());
		}
			return obj;
	}
	
	public Loan insert(Loan loan) {
		
		Client cli = servCli.find(loan.getClient().getId());
		Book book = servBook.find(loan.getBook().getId());
		
		loan.setId(null);
		loan.setClient(validationClient(loan.getClient().getId()));
		
		
		cli.setStatus(StatusClient.PENDENTE);
		servCli.update(cli);
		
		loan.setLoanDay(LocalDate.now().toString(dtf));
		loan.setLoanReturnDay(LocalDate.now().plusDays(15).toString(dtf));
		loan.setBook(validationBook(loan.getBook().getId()));
		loan.setLoanStatus(LoanStatus.OK);
		
		if(book.getAmount() == 1) {
			book.setBookStatus(BookStatus.UNICO);
			throw new BookUnavailableForLoan("O Livro está indisponível para empréstimo");
		}else {
			book.setAmount(book.getAmount() -1);
		}
		
		servBook.update(book);
		return repoLoan.save(loan);
		
	}
	
	public Loan renewal(Loan loan) {
		Loan loanRenewal = find(loan.getId());
		loanRenewal.setLoanDay(LocalDate.now().toString(dtf));
		loanRenewal.setLoanReturnDay(LocalDate.now().plusDays(15).toString(dtf));
		loanRenewal.setLoanStatus(LoanStatus.RENOVADO);
		return repoLoan.save(loanRenewal);
	}
	
	public Loan returned(Loan loan) {
		Loan loanReturned = find(loan.getId());
		loanReturned.setLoanStatus(LoanStatus.DEVOLVIDO);
		Book book = servBook.find(loanReturned.getBook().getId());
		book.setAmount(book.getAmount() + 1);
		if(book.getAmount() > 1) {
			book.setBookStatus(BookStatus.DISPONIVEL);
		}
		servBook.update(book);
		
		Client client = servCli.find(loanReturned.getClient().getId());
		client.setStatus(StatusClient.DISPONIVEL);
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
		if(cli.getStatus() == StatusClient.PENDENTE) {
			throw new CustomerWithOpenLoan("O usuário está com um empréstimo pendente!");
		}
		return cli;
	}
	
	public Book validationBook(Integer id) {
		Book book = servBook.find(id);
		
		if(book.getAmount() <= 1) {
			throw new BookUnavailableForLoan("O Livro não pode ser Emprestado");
		}
		
		if(book.getBookStatus() == BookStatus.UNICO || book.getBookStatus() == BookStatus.EMPRESTADO) {
			throw new BookUnavailableForLoan("O Livro não pode ser Emprestado");
		}
		
		return book;
	}
	
	public Loan fromDTO(LoanNewDTO objDto) {
		return new Loan(objDto.getId(), servBook.find(objDto.getBookId()), servCli.find(objDto.getClientId()));
	}
	
}