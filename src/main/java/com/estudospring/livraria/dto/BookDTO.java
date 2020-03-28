package com.estudospring.livraria.dto;

import org.springframework.data.domain.Page;

import com.estudospring.livraria.domain.Book;
import com.estudospring.livraria.domain.Category;
import com.estudospring.livraria.domain.enums.BookStatus;

public class BookDTO {
	
	private Integer id;
	private String name;
	private String author;
	private Integer edition;
	private BookStatus bookStatus;
	private Integer amount;
	private Integer category;
	
	public BookDTO(Book book) {
		this.id = book.getId();
		this.name = book.getName();
		this.author = book.getAuthor();
		this.edition = book.getEdition();
		this.bookStatus = book.getBookStatus();
		this.amount = book.getAmount();
		this.category = book.getCategory().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}

	public BookStatus getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public static Page<BookDTO> converter(Page<Book> books){
		return books.map(BookDTO::new);
	}

	
	
	
}
