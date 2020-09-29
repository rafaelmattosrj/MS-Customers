package br.com.rafaelmattos.MSCustomers.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.rafaelmattos.MSCustomers.domain.Address;
import br.com.rafaelmattos.MSCustomers.domain.Customer;
import br.com.rafaelmattos.MSCustomers.service.validation.CustomerUpdate;
import lombok.Data;

@Data
@CustomerUpdate
public class CustomerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=20, message="O tamanho deve ser entre 3 e 20 caracteres")
	private String firstName;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=40, message="O tamanho deve ser entre 3 e 40 caracteres")
	private String lastName;
	//private String fullName;
    
	@NotEmpty(message="Preenchimento obrigatório")
    @Max(11) @Min(11)
	private String cpf;
    
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;	
	
	@NotEmpty(message="Preenchimento obrigatório")
	private LocalDate birthDate;
	
	private List<String> phoneNumber = new ArrayList<>(); 
	
	private Address address;


	public CustomerDTO(Customer obj) {
	}

}