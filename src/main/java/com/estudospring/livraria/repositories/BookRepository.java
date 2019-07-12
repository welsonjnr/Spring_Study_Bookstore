package com.estudospring.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudospring.livraria.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
