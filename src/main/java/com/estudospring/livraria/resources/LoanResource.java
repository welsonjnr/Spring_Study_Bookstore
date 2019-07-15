package com.estudospring.livraria.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudospring.livraria.domain.Loan;
import com.estudospring.livraria.services.LoanService;

@RestController
@RequestMapping(value= "/loans")
public class LoanResource {
	
	@Autowired
	private LoanService loanServi;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find (@PathVariable Integer id){
		Loan obj = loanServi.find(id);
		
		return ResponseEntity.ok().body(obj);
	
	}
}