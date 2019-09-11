package com.estudospring.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.repositories.BookRepository;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public Book find(Integer idBook) {
		Optional<Book> book = bookRepo.findById(idBook);
		
		if(book == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + idBook + ", Tipo: " + Book.class.getName());
		}
		return book.orElse(null);
	}
	
	public Book insert(Book book) {
		book.setId(null);
		return bookRepo.save(book);	
	}
	
	public Book update(Book book) {
		Book bookUpdate = find(book.getId());
		updateData(bookUpdate, book);
		return bookUpdate;
	}
	
	public void delete(Integer idBook) {
		find(idBook);
		try {
			bookRepo.deleteById(idBook);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Não foi possível excluir, poís, possuem outras categorias relacionada a essa!");
		}
	}
	
	public List<Book> findAll() {
		return bookRepo.findAll();
	}
	
	public Page<Book> findPage(Integer pages, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(pages, linesPerPage, Direction.valueOf(direction), orderBy);
		return bookRepo.findAll(pageRequest);
	}
	
	private void updateData(Book bookUpdate, Book book) {
		if(book != null) bookUpdate.setAuthor(book.getAuthor());
		if(book != null) bookUpdate.setBookStatus(book.getBookStatus());
		if(book != null) bookUpdate.setEdition(book.getEdition());
		if(book != null) bookUpdate.setName(book.getName());
		if(book != null) bookUpdate.setCategory(book.getCategory());
	}
	
}
