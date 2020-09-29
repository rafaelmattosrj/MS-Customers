package br.com.rafaelmattos.MSCustomers.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;

//Data de cadastro, Nome completo, Telefone(Opcional), 
//Celular, Email, Endereço, CPF(CHAVE PRIMÁRIA) e Data de nascimento.
//Buscar o cliente = CPF, Email, Celular, Nome completo, Nome parcial.
//Todos os campos podem ser atualizados exceto CPF.
//A Aplicação deve ter capacidade de excluir o cliente pelo CPF.
//Como segurança também deve ser informado o primeiro nome para conferência dupla. 


@Data
@Entity
public class Customer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(name="UUID", nullable = false, length = 36)
	private String uuid = UUID.randomUUID().toString();

    @Column(name = "REGISTRATION_DATE", nullable = false)
	private LocalDateTime registrationDate;
    
    @NotNull
    @Column(name = "FIRST_NAME")
	private String firstName;
    
    @NotNull
    @Column(name = "LAST_NAME")
	private String lastName;
    
    @NotNull
    @Max(11) @Min(11)
    @Column(name = "CPF")
	private String cpf;
    
    @javax.validation.constraints.Email
    @NotBlank
    @Column(name = "EMAIL", nullable = false, length = 100)
	private String email;	
	
    @Column(name = "BIRTH_DATE")
	private LocalDate birthDate;
	
	@ElementCollection
	@CollectionTable(name="PHONE_NUMBER")
	private List<String> phoneNumber = new ArrayList<>(); 
	
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address address;	

	public Customer(Integer id, String uuid, String firstName, String lastName, String cpf, String email) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.email = email;
	}
	
}
