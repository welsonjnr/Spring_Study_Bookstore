package com.estudospring.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.domain.enums.BookStatus;
import com.estudospring.livraria.dto.BookDTO;
import com.estudospring.livraria.repositories.BookRepository;
import com.estudospring.livraria.services.exceptions.ObjectNotFoundException;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepo;

	public Book find(Integer idBook) {
		Optional<Book> book = bookRepo.findById(idBook);

		if (book == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + idBook + ", Tipo: " + Book.class.getName());
		}
		return book.orElse(null);
	}

	public List<Book> findByNameBook(String nameBook) {
		List<Book> obj = bookRepo.findByNameContaining(nameBook);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto not found! Nome do Livro: " + nameBook + ", Tipo: " + Book.class.getName());
		}
		return obj;
	}
	
	public List<Book> findByAuthorBook(String author) {
		List<Book> obj = bookRepo.findByAuthorContaining(author);

		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto not found! Nome do Livro: " + author + ", Tipo: " + Book.class.getName());
		}
		return obj;
	}

	public List<Book> findByFiltro(Book filtro){
		Example example = Example.of(filtro, ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		
		return bookRepo.findAll(example);
	}
	
	public Book insert(Book book) {
		book.setId(null);
		if(book.getAmount() == 1) {
			book.setBookStatus(BookStatus.UNICO);
		}else {
			book.setBookStatus(BookStatus.DISPONIVEL);
		}
		book.setCategory(book.getCategory());
		return bookRepo.save(book);
	}

	public Book update(Book book) {
		Book bookUpdate = find(book.getId());
		updateData(bookUpdate, book);
		return bookRepo.save(bookUpdate);
	}

	public void delete(Integer idBook) {
		find(idBook);
		try {
			bookRepo.deleteById(idBook);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Não foi possível excluir, poís, possuem outras categorias relacionada a essa!");
		}
	}

	public List<Book> findAll() {
		return bookRepo.findAll();
	}

	public Page<Book> findPage(Integer pages, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(pages, linesPerPage, Direction.valueOf(direction), orderBy);
		return bookRepo.findAll(pageRequest);
	}

	private void updateData(Book bookUpdate, Book book) {
		if (book.getAuthor() != null) {
			bookUpdate.setAuthor(book.getAuthor());
		}
		if (book.getBookStatus() != null) {
			bookUpdate.setBookStatus(book.getBookStatus());
		}
		if (book.getEdition() != null) {
			bookUpdate.setEdition(book.getEdition());
		}
		if (book.getName() != null) {
			bookUpdate.setName(book.getName());
		}
		if (book.getCategory() != null) {
			bookUpdate.setCategory(book.getCategory());
		}
		if (book.getAmount() != null) {
			bookUpdate.setAmount(book.getAmount());
			if(bookUpdate.getAmount() > 1) {
				bookUpdate.setBookStatus(BookStatus.DISPONIVEL);
			}if(bookUpdate.getAmount() == 1) {
				book.setBookStatus(BookStatus.UNICO);
			}
		}
	}

	public Book fromDTO(BookDTO objDto) {
		return new Book(objDto.getId(), objDto.getName(), objDto.getAuthor(), objDto.getEdition(),
				objDto.getBookStatus(), objDto.getAmount(), (objDto.getCategory()));
	}

}
