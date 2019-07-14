package com.estudospring.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudospring.livraria.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
