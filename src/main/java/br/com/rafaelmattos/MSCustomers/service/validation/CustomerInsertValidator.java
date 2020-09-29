package br.com.rafaelmattos.MSCustomers.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.rafaelmattos.MSCustomers.controller.exception.FieldMessage;
import br.com.rafaelmattos.MSCustomers.domain.Customer;
import br.com.rafaelmattos.MSCustomers.dto.CustomerNewDTO;
import br.com.rafaelmattos.MSCustomers.repository.CustomerRepository;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {
	
	@Autowired
	private CustomerRepository repo;
	
	@Override
	public void initialize(CustomerInsert ann) {
	}

	@Override
	public boolean isValid(CustomerNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
// inclua os testes aqui, inserindo erros na lista
		
		Customer aux = repo.findByCPF(objDto.getCpf());
		if (aux != null) {
			list.add(new FieldMessage("CPF", "CPF já existente"));
		}

		Customer aux1 = repo.findByEmail(objDto.getEmail());
		if (aux1 != null) {
			list.add(new FieldMessage("Email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}