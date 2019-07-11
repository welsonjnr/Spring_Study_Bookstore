package com.estudospring.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudospring.livraria.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
