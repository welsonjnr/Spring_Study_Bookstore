package com.estudospring.livraria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estudospring.livraria.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	@Transactional
	List<Book> findByNameContaining(String name);
}
