package com.estudospring.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudospring.livraria.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

}
