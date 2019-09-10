package com.estudospring.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Loan find(Integer id) {
		Optional<Loan> obj = repoLoan.findById(id);

		if (obj == null) {
			throw new ObjectNotFoundException("Objeto not found! Id: " + id + ", Tipo: " + Loan.class.getName());
		}
		return obj.orElseThrow(null);
	}
	
	public Loan fromDTO(LoanDTO objDto) {
		Client cli = new Client(null, objDto.getNameClient(), null, null, null, null, null, null);
		Book book = new Book(objDto.getBookId(), objDto.getNameBook(), null, null, null);
		Loan loan = new Loan(objDto.getId(), null, LoanStatus.toEnum(objDto.getStatus()), book, cli);
		return loan;
	}
	
	public List<Loan> findAll() {
		return repoLoan.findAll();
	}
	
}