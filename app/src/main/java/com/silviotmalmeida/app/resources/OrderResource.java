package com.silviotmalmeida.app.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silviotmalmeida.app.entities.Order;
import com.silviotmalmeida.app.services.OrderService;

// classe que implementa o controller da entidade Order
// implementa o endpoint "/orders"
@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	// injetando o service da entidade Order
	@Autowired
	private OrderService service;

	// método que retorna todos os registros
	// acessível via método GET
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {

		// obtendo a lista de registros
		List<Order> list = this.service.findAll();

		// retorna a resposta com status OK e registros no body
		return ResponseEntity.ok().body(list);
	}

	// método que retorna o registro do id selecionado
	// acessível via método GET, adicionando o parâmetro /id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {

		// obtendo o registro
		Order obj = this.service.findById(id);

		// retorna a resposta com status OK e registros no body
		return ResponseEntity.ok().body(obj);
	}

}
