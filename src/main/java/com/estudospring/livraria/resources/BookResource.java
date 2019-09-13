package com.estudospring.livraria.resources;

import java.net.URI;
import java.util.List;

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
	public ResponseEntity<Book> insert(@Valid @RequestBody Book book){
		book = bookServ.insert(book);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody Book bookUpdate, @PathVariable Integer id){
		bookUpdate.setId(id);
		bookUpdate = bookServ.update(bookUpdate);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		bookServ.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Book>> findAll(){
		List<Book> list = bookServ.findAll();
		return ResponseEntity.ok().body(list);
		
	}
	
	public ResponseEntity<Page<Book>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue= "nome") String orderBy,
			@RequestParam(value="direction",defaultValue ="ASC" ) String direction){
			
			Page<Book> list = bookServ.findPage(page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
	}
	
}