package com.estudospring.livraria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estudospring.livraria.domain.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

	@Transactional
	List<Loan> findByClientNameContaining(String name);
	@Transactional
	List<Loan> findByClientCpfContaining(String cpf);
	@Transactional
	List<Loan> findByBookNameContaining(String nameBook);
	@Transactional
	List<Loan> findByBookAuthorContaining(String auhtor);
}
