package br.com.rafaelmattos.MSCustomers.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.rafaelmattos.MSCustomers.controller.exception.FieldMessage;
import br.com.rafaelmattos.MSCustomers.domain.Customer;
import br.com.rafaelmattos.MSCustomers.dto.CustomerDTO;
import br.com.rafaelmattos.MSCustomers.repository.CustomerRepository;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private CustomerRepository repo;
	
	@Override
	public void initialize(CustomerUpdate ann) {
	}

	@Override
	public boolean isValid(CustomerDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings(value = "unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
// inclua os testes aqui, inserindo erros na lista
		
		Customer aux = repo.findByCPF(objDto.getCpf());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("CPF", "CPF já existente"));
		}
		
		Customer aux1 = repo.findByEmail(objDto.getEmail());
		if (aux1 != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}