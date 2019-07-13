package com.estudospring.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudospring.livraria.domain.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
