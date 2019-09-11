package com.estudospring.livraria.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.domain.Loan;
import com.estudospring.livraria.domain.enums.LoanStatus;
import com.estudospring.livraria.dto.LoanDTO;
import com.estudospring.livraria.repositories.LoanRepository;
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
		obj.setLoanDay(new Date());
		obj.setStatus(LoanStatus.BORROWED.getCod());
		obj.setClient(servCli.find(obj.getClient().getId()));
		obj.setBook(servBook.find(obj.getBook().getId()));
		return repoLoan.save(obj);
	}
	
	public Loan update(Loan obj) {
		Loan newLoan = find(obj.getId());
		updateData(newLoan, obj);
		return repoLoan.save(newLoan);
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
	
	public Loan fromDTO(LoanDTO objDto) {
		Client cli = new Client(null, objDto.getNameClient(), null, null, null, null, null, null);
		Book book = new Book(objDto.getBookId(), objDto.getNameBook(), null, null, null, null);
		Loan loan = new Loan(objDto.getId(), null, LoanStatus.toEnum(objDto.getStatus()), book, cli);
		return loan;
	}
	
	private void updateData(Loan newLoan, Loan obj) {
		newLoan.setStatus(obj.getStatus());
		newLoan.setLoanDay(obj.getLoanDay());
		newLoan.setBook(obj.getBook());
		newLoan.setClient(obj.getClient());
	}
	
}