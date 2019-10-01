package com.estudospring.livraria.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Loan;
import com.estudospring.livraria.domain.enums.BookStatus;
import com.estudospring.livraria.domain.enums.LoanStatus;
import com.estudospring.livraria.repositories.LoanRepository;
import com.estudospring.livraria.services.exceptions.BookIsNotAvailableForRenewal;
import com.estudospring.livraria.services.exceptions.BookUnavailableForLoan;
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
	
	@Transactional
	public Loan insert(Loan obj) {
		obj.setId(null);
		obj.setLoanDay(LocalDate.now());
//	obj.setLoanReturnDay(obj.getLoanDay());
		obj.setLoanStatus(LoanStatus.BORROWED);
		obj.setClient(servCli.find(obj.getClient().getId()));
		obj.setBook(servBook.find(obj.getBook().getId()));
		
		if(obj.getBook().getBookStatus().equals(BookStatus.SINGLE) || obj.getBook().getBookStatus().equals(BookStatus.BORROWED)) {
			throw new BookUnavailableForLoan("O livro é unico ou já esta emprestado");
		}
		
		obj.getBook().setAmount(obj.getBook().getAmount() - 1);
		
		if(obj.getBook().getAmount().equals(1)) {
			obj.getBook().setBookStatus(BookStatus.SINGLE);
			}
		
		return repoLoan.save(obj);
		
	}
	
	public Loan returnedBook(Loan loan) {
		loan = find(loan.getId());
		if(loan.getLoanStatus() != LoanStatus.CANCELED) {
			loan.setLoanStatus(LoanStatus.RETURNED);
		}
		loan.getBook().setAmount(+ 1);
		loan.getBook().setBookStatus(BookStatus.AVAILABLE);
		return repoLoan.save(loan);
	}

	public Loan renovatedStatus(Loan loan) {
		loan = find(loan.getId());
		if(loan.getLoanStatus() == LoanStatus.LATE || loan.getLoanStatus() == LoanStatus.BORROWED && loan.getLoanStatus() != LoanStatus.RETURNED) {
			loan.setLoanStatus(LoanStatus.RENOVATED);
			loan.setLoanDay(LocalDate.now());
		}
		else{
			throw new BookIsNotAvailableForRenewal("O livro já foi devolvido ou o Empréstimo foi Cancelado!");
		}
		return repoLoan.save(loan);
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

}