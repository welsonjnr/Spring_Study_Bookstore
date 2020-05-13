package com.estudospring.livraria.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudospring.livraria.domain.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

	Optional<UserLogin> findByEmail(String email);
	
}
