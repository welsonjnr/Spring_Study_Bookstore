//CLASSE DE VALIDAÇÃO DA ATUALIZAÇÃO DO CLIENTE

package com.estudospring.livraria.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.estudospring.livraria.domain.Client;
import com.estudospring.livraria.dto.ClientDTO;
import com.estudospring.livraria.repositories.ClientRepository;
import com.estudospring.livraria.resources.exception.FieldMessage;

/*
 * O ConstraintValidator tem que receber o nome da classe que vai receber a validação e o tipo de dados que vai aceitar a validação 
 * Classe para a alteração de emails no banco de dados.
 */
public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

	/*
	 * Necessário pois, as informações presentes para a atualização de um email de um cliente não possui o id, pois o id já vai no código URI
	 * Então essa variável e necessária para que pegue o id no próprio código http
	 */
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void initialize(ClientUpdate ann) {
	}
	
	//Dentro desse método que vai ser implementado a nossa lógica de validação do objeto recebido
	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
		/*
		 * Método necessário pois o request vai pegar o id com chave valor (do map) no banco de dados
		 * Casting convertendo o objeto genérico no tipo que eu preciso
		 */
		
		@SuppressWarnings("unchecked")
		/*
		 * Vou mapear um string com um chave do tipo String
		 *                               Casting
		*/
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		//Pegando o id
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		
		/*
		 * Esse método findByEmail foi "implementado" no ClientRepository. 
		 * Com a ajuda do Spring, poís ele apresenta ferramentas para o desenvolvimento.
		 * Após, é necessário a pesquisa no banco de dados para que não haja email repetidos
		 */
		Client aux = clientRepository.findByEmail(objDto.getEmail());

		/*
		 * Pegando o variável auxiliar aux e conferindo se existe alguem id igual.
		 * E adicionando A mensagem no campo de texto da classe FieldMessage
		 */
		
		if(aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente!"));
		}
		
		/*
		 * inclua os teste aqui, inserindo erros na lista
		 * Vai percorrer a lista de FieldMessage e para cada objeto na lista adicionar um erro correspondente na lista do framework
		 * Pegar os meus erros e adicionar a lista do framework Spring
		 */
		
		for(FieldMessage e: list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage()).addConstraintViolation();
		}
		
		return list.isEmpty();
		
	}
	
}
