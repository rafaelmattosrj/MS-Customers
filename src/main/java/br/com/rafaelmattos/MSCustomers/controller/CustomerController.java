package br.com.rafaelmattos.MSCustomers.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rafaelmattos.MSCustomers.domain.Customer;
import br.com.rafaelmattos.MSCustomers.dto.CustomerDTO;
import br.com.rafaelmattos.MSCustomers.dto.CustomerNewDTO;
import br.com.rafaelmattos.MSCustomers.service.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	// Instanciar o objeto
	@Autowired
	private CustomerService service;

	// @ApiOperation(value="Busca por id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> find(@PathVariable Integer id) {
		Customer obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	// Inserir
	// @ApiOperation(value="Insere cliente")
	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CustomerNewDTO objDto) {
		Customer obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// @ApiOperation(value="Atualiza cliente")
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> find(@Valid @RequestBody CustomerDTO objDto, @PathVariable Integer id) {
		Customer obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	// @ApiOperation(value="Remove cliente")
	@Transactional
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	// @ApiOperation(value="Retorna todas clientes")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CustomerDTO>> findAll() {
		List<Customer> list = service.findAll();
		List<CustomerDTO> listDto = list.stream().map(obj -> new CustomerDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	// @ApiOperation(value="Retorna todas clientes com paginação")
	// categorias/page?page=01&linesPerPage=20...
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CustomerDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Customer> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CustomerDTO> listDto = list.map(obj -> new CustomerDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
