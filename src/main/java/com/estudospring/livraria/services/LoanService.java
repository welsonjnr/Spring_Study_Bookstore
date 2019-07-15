package com.estudospring.livraria.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Loan;
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
		return obj.orElse(null);
	}
}