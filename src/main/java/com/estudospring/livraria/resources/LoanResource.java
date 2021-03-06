package com.estudospring.livraria.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.domain.Loan;
import com.estudospring.livraria.dto.LoanDTO;
import com.estudospring.livraria.dto.LoanNewDTO;
import com.estudospring.livraria.services.BookService;
import com.estudospring.livraria.services.ClientService;
import com.estudospring.livraria.services.LoanService;

@RestController
@RequestMapping(value= "/library/loans")
public class LoanResource {
	
	@Autowired
	private LoanService loanServ;
	
	@Autowired
	private BookService bookServ;
	
	@Autowired 
	private ClientService clientServ;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Loan> find (@PathVariable Integer id){
		Loan obj = loanServ.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<LoanNewDTO> insert(@Valid @RequestBody LoanNewDTO newLoan){
		Loan loan = loanServ.fromDTO(newLoan);
		loan = loanServ.insert(loan);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(loan.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}


	@GetMapping
	public ResponseEntity<List<Loan>> findByFiltro(@RequestParam (value = "nameclient", required = false) String nameClient,
												   @RequestParam (value = "cpf", required = false) String cpf,
												   @RequestParam (value = "namebook", required = false) String nameBook,
												   @RequestParam (value = "nameauthor", required = false) String nameAuthor){
		List<Loan> loans = new ArrayList<Loan>();
		
		if(nameClient != null) {
			loans = loanServ.findLoanByNameClient(nameClient);
		}
		if(cpf != null && loans.isEmpty()) {
			loans = loanServ.findLoanByCpfClient(cpf);
		}
		if(nameBook != null && loans.isEmpty()) {
			loans = loanServ.findLoanByNameBook(nameBook);
		}
		if(nameAuthor != null && loans.isEmpty()) {
			loans = loanServ.findLoanByAuthorBook(nameAuthor);
		}
		
		if(loans.isEmpty()) {
			loans = loanServ.findAll();
		}
		
		return ResponseEntity.ok(loans);
	}
	
	@PutMapping(value="/renew/{id}")
	public ResponseEntity<Loan> renewLoan(@Valid @PathVariable Integer id , @RequestBody LoanNewDTO renewLoan){
		Loan loan = loanServ.fromDTO(renewLoan);
		loan.setId(id);
		loan = loanServ.renewal(loan);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/return/{id}")
	public ResponseEntity<Loan> returnedBook(@Valid @PathVariable Integer id, @RequestBody Loan loan){
		loan.setId(id);
		loan = loanServ.returned(loan);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		loanServ.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value ="/all")
	public ResponseEntity<List<LoanDTO>> findAll(){
		List<Loan> list = loanServ.findAll();
		
		List<LoanDTO> listDto = list.stream().map(obj -> new LoanDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value="/find/page")
	public ResponseEntity<Page<LoanDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue= "loanDay") String orderBy,
			@RequestParam(value="direction",defaultValue ="ASC" ) String direction){
			
			Page<Loan> list = loanServ.findPage(page, linesPerPage, orderBy, direction);
			Page<LoanDTO> listDto = list.map(obj -> new LoanDTO(obj));
			return ResponseEntity.ok().body(listDto);
			}
}
