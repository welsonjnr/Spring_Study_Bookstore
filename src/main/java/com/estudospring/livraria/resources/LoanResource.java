package com.estudospring.livraria.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estudospring.livraria.domain.Loan;
import com.estudospring.livraria.dto.LoanDTO;
import com.estudospring.livraria.services.LoanService;

@RestController
@RequestMapping(value= "/loans")
public class LoanResource {
	
	@Autowired
	private LoanService loanServ;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find (@PathVariable Integer id){
		Loan obj = loanServ.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<LoanDTO>> findAll(){
		List<Loan> list = loanServ.findAll();
		/*
		 * Método muito interessante. Ele vai pegar uma lista de Cliente-> Transformar
		 * em stream. Vai mapear cada stream e em cada uma ele vai transformar em apenas
		 * o objeto ClienteDTO. Depois vai coletar elas e transformar novamente em
		 * lista. GENIUS!!
		 */
		List<LoanDTO> listDto = list.stream().map(obj -> new LoanDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
			
		}
	
}