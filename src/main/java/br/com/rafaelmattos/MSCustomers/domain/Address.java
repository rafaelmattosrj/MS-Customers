package br.com.rafaelmattos.MSCustomers.domain;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

//CEP, Rua, Numero, Complemento, Bairro, Cidade, Estado, País 

@Data
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = -6899347536442203112L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "UUID", nullable = false, length = 36, unique = true)
    private String uuid = UUID.randomUUID().toString();
    
    @Column(name = "TYPE", nullable = false, length = 20)
    private String type;
    
    @Column(name = "ZIP_CODE", nullable = false, length = 20)
    private String zipCode;
    private String street;
    
    @Column(name = "NUMBER_ADDRESS", nullable = false, length = 20)
    private String numberAddress;
    
    @Column(name = "COMPLEMENT", nullable = false, length = 20)
    private String complement;
    
    @Column(name = "NEIGHBORHOOD", nullable = false, length = 45)
    private String neighborhood;
    
    @Column(name = "CITY", nullable = false, length = 45)
    private String city;
    
    @Max(2)
    @Min(2)
    @Column(name = "STATE", nullable = false, length = 2)
    private String state;
    
    @Column(name = "COUNTRY", nullable = false, length = 25)
    private String country;
    
    @Column(name = "ADDITIONAL_ADDRESS_INFORMATION")
    private String additionalAddressInformation;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

	public Address(Integer id, String uuid, String type, String zipCode, String street, String numberAddress,
			String complement, String neighborhood, String city, String state, String country,
			String additionalAddressInformation, Customer customer) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.type = type;
		this.zipCode = zipCode;
		this.street = street;
		this.numberAddress = numberAddress;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.country = country;
		this.additionalAddressInformation = additionalAddressInformation;
		this.customer = customer;
	}

	public void add(Address end) {	
	}

}