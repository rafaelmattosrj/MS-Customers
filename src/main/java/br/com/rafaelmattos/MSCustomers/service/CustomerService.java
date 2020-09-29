package br.com.rafaelmattos.MSCustomers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.rafaelmattos.MSCustomers.domain.Address;
import br.com.rafaelmattos.MSCustomers.domain.Customer;
import br.com.rafaelmattos.MSCustomers.dto.CustomerDTO;
import br.com.rafaelmattos.MSCustomers.dto.CustomerNewDTO;
import br.com.rafaelmattos.MSCustomers.repository.AddressRepository;
import br.com.rafaelmattos.MSCustomers.repository.CustomerRepository;
import br.com.rafaelmattos.MSCustomers.service.exception.DataIntegrityException;
import br.com.rafaelmattos.MSCustomers.service.exception.ObjectNotFoundException;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;

	@Autowired
	private AddressRepository addressRepository;

	// buscar no banco de dados com base no id
	public Customer find(Integer id) {

		Optional<Customer> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Não encontrado! Id: " + id + ", Tipo: " + Customer.class.getName()));
	}

	// Inserir
	public Customer insert(Customer obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.save(obj.getAddress());
		return obj;
	}

	// Atualizar
	public Customer update(Customer obj) {
		Customer newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	// Deletar
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir.");
		}
	}

	// Buscar
	public List<Customer> findAll() {
		return repo.findAll();
	}

	// Paginação
	public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	// Construção
	public Customer fromDTO(CustomerDTO objDto) {
		return new Customer(null, null, objDto.getFirstName(), objDto.getLastName(), 
				objDto.getCpf(), objDto.getEmail());
	}

	// Construção
	public Customer fromDTO(CustomerNewDTO objDto) {
		Customer cus = new Customer(null, null, objDto.getFirstName(), objDto.getLastName(), 
				objDto.getCpf(), objDto.getEmail());
		Address end = new Address(null, null, objDto.getType(), objDto.getZipCode(),objDto.getStreet(),
				objDto.getNumberAddress(), objDto.getComplement(), objDto.getNeighborhood(), objDto.getCity(), objDto.getState(),
				objDto.getCountry(), objDto.getAdditionalAddressInformation(), cus);
		cus.getAddress().add(end);
		cus.getPhoneNumber().add(objDto.getPhoneNumber1());
		if (objDto.getPhoneNumber2() != null) {
			cus.getPhoneNumber().add(objDto.getPhoneNumber2());
		}
		return cus;
	}

	private void updateData(Customer newObj, Customer obj) {
		newObj.setFirstName(obj.getFirstName());
		newObj.setLastName(obj.getLastName());
		newObj.setEmail(obj.getEmail());
		newObj.setBirthDate(obj.getBirthDate());
		newObj.setPhoneNumber(obj.getPhoneNumber());
		newObj.setAddress(obj.getAddress());
	}
}
