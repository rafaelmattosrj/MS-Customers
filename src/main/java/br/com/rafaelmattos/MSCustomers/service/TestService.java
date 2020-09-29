//package br.com.rafaelmattos.MSCustomers.service;
//
//import java.text.ParseException;
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import br.com.rafaelmattos.MSCustomers.domain.Address;
//import br.com.rafaelmattos.MSCustomers.domain.Customer;
//import br.com.rafaelmattos.MSCustomers.repository.AddressRepository;
//import br.com.rafaelmattos.MSCustomers.repository.CustomerRepository;
//
//@Service
//public class TestService {
//
//	@Autowired
//	private CustomerRepository customerRepository;
//	
//	@Autowired
//	private AddressRepository addressRepository;
//
//	public void instantiateTestDatabase() throws ParseException {
//
//		Customer cus1 = new Customer(null, null, "Rafael", "Mattos", "04973412040", "rafael@live.com");
//		cus1.getPhoneNumber().addAll(Arrays.asList("27363323", "93838393"));
//		
//		Customer cus2 = new Customer(null, null, "Paulo", "Eduardo", "04973412040", "peom@live.com");
//		cus2.getPhoneNumber().addAll(Arrays.asList("2345678"));
//		
//		Address end1 = new Address(null, null, "Apartamento", "22040011", "Rua Santa Clara", "150", "501", "Copacabana", "Rio de Janeiro", "RJ", "Brasil", "Perto da esquina", cus1);
//		Address end2 = new Address(null, null, "Apartamento", "22045011", "Rua Anita Garilbade", "50", "201", "Copacabana", "Rio de Janeiro", "RJ", "Brasil", "Perto da esquina", cus2);
//		
//		customerRepository.saveAll(Arrays.asList(cus1, cus2));
//		addressRepository.saveAll(Arrays.asList(end1, end2));
//
//
//	}
//
//}
