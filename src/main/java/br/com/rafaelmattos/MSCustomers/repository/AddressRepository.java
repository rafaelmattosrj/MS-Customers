package br.com.rafaelmattos.MSCustomers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rafaelmattos.MSCustomers.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}