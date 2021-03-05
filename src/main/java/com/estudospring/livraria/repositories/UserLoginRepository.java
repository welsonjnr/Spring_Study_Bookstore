
package com.estudospring.livraria.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudospring.livraria.domain.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {

	boolean existsByEmail(String email);
	
	Optional<UserLogin> findByEmail(String email);

}
