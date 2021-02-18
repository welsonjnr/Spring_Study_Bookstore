package com.estudospring.livraria.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.estudospring.livraria.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

	/*
	 * readOnly= significa que ela não necessita ser envolvida na transação de banco
	 * de dados. O Spring faz automática um método findByEmail para que não se possa
	 * repetir email no banco.
	 */

	@Transactional(readOnly = true)
	Client findByEmail(String email);

	@Transactional
	List<Client> findByNameContaining(String name);
}
