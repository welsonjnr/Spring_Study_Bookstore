package com.estudospring.livraria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudospring.livraria.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
