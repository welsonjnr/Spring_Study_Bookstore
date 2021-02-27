package com.estudospring.livraria.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.dto.BookDTO;
import com.estudospring.livraria.services.BookService;

@RestController
@RequestMapping(value="/books")
public class BookResource {
	
	@Autowired
	private BookService bookServ;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id){
		Book book = bookServ.find(id);
		return ResponseEntity.ok().body(book);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<BookDTO> insert(@Valid @RequestBody BookDTO bookDto){
		Book book = bookServ.fromDTO(bookDto);
		book = bookServ.insert(book);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
		return ResponseEntity.created(uri).body(new BookDTO(book));
	}

	@GetMapping(value = "/findBookNameUnico/{nameBook}")
	public ResponseEntity<Book> findByNameBookUnico(@PathVariable String nameBook){
		Book book = new Book();
		if(nameBook.length() <= 3) {
			book = bookServ.findByNameBookUnico(nameBook);
		}
		else {
		String param = nameBook.replace(nameBook.substring(0, 1), nameBook.substring(0, 1).toUpperCase());
		    book = bookServ.findByNameBookUnico(param);
		}
		return ResponseEntity.ok().body(book);
	}
	
	@GetMapping(value = "/findBookName/{nameBook}")
	public ResponseEntity<List<Book>> findByNameBook(@PathVariable String nameBook){
		List<Book> obj = new ArrayList<Book>();
		if(nameBook.length() <= 3) {
			obj = bookServ.findByNameBook(nameBook);
		}
		else {
		String param = nameBook.replace(nameBook.substring(0, 1), nameBook.substring(0, 1).toUpperCase());
		    obj = bookServ.findByNameBook(param);
		}
		return ResponseEntity.ok().body(obj);
	}
	
	@PutMapping(value="/{id}")
	@Transactional
	public ResponseEntity<Void> update(@PathVariable Integer id, @Valid @RequestBody BookDTO bookUpdate){
		Book book = bookServ.fromDTO(bookUpdate);
		book.setId(id);
		book  = bookServ.update(book);
	
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Integer id){
		bookServ.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> findAll(){
		List<Book> list = bookServ.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping(value="/principal")
	public ResponseEntity<List<Book>> findAllPrincipal(){
		List<Book> list = bookServ.findAll();
		List<Book> subList = list;
		if(list.size() > 6) {
			subList = list.subList(0, 6);
		}
		return ResponseEntity.ok().body(subList);
	}
	
	@GetMapping(value="/find/page")
	public ResponseEntity<Page<Book>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue= "nome") String orderBy,
			@RequestParam(value="direction",defaultValue ="ASC" ) String direction){
			
			Page<Book> list = bookServ.findPage(page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
	}
	
}