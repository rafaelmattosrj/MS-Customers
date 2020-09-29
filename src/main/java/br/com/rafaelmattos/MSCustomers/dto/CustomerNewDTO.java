package br.com.rafaelmattos.MSCustomers.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.rafaelmattos.MSCustomers.service.validation.CustomerInsert;
import lombok.Data;

@Data
@CustomerInsert
public class CustomerNewDTO implements Serializable {
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
	
    @NotBlank
    private String phoneNumber1;
    private String phoneNumber2;
	
    @NotBlank
    private String type;
    @NotBlank
    private String zipCode;
    @NotBlank
    private String street;
    @NotBlank
    private String numberAddress;

    private String complement;
    
    @NotBlank
    private String neighborhood;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    
    private String additionalAddressInformation;	
	
}