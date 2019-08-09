package com.estudospring.livraria.services.validation;

import javax.validation.Payload;

/*
 * Criação da nossa própria validação para a validação do CPF. Onde se aplicará a regra do negócio
 * Classe Padrão
 */

public @interface ClientInsert {
	String message() default "Erro de Validação";
	
	Class<?>[] groups() default{};
	
	Class <? extends Payload>[] payload() default{};

}
