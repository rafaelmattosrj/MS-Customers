package br.com.rafaelmattos.MSCustomers.repository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelmattos.MSCustomers.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Transactional(readOnly = true)
	Customer findByCPF(String cpf);
	
	@Transactional(readOnly = true)
	Customer findByEmail(String email);
	
}
