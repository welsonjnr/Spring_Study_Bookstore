package com.estudospring.livraria.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=ClientUpdateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

/*
 * Criação da nossa própria validação para a atualização da classe Client no banco de dados. Onde se aplicará a regra do negócio
 * Classe Padrão
 */

public @interface ClientUpdate {

		String message() default "Erro de Validação!";
		
		Class<?>[] groups() default{};
		
		Class<? extends Payload> [] payload() default{}; 

}
