package com.estudospring.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudospring.livraria.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
