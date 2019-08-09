package com.estudospring.livraria.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.dto.ClientNewDTO;
import com.estudospring.livraria.repositories.ClientRepository;
import com.estudospring.livraria.resources.exception.FieldMessage;

//Trabalhar mais na implementação dessa classe
public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {
	
	@Autowired
	private ClientRepository cliRepo;

	//Imagino que seja o método para iniciar no sobreposição
	@Override
	public void initialize(ClientInsert ann) {	
	}
	
	/*
	 * Método que vem do extensão dessa classe ConstraintValidator
	 * Dentro desse método que vai ser implementado a nossa lógica de validação do objeto recebido
	*/
	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Client aux = cliRepo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email","Existig Email!"));
		}
		//Método padrão para descontruir a lista de erros presente e adicionar os seus
		for(FieldMessage e: list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage()).addConstraintViolation();
		}
		
		return list.isEmpty();
	}
	
	
	
}
