package com.silviotmalmeida.app.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silviotmalmeida.app.entities.User;
import com.silviotmalmeida.app.services.UserService;

// classe que implementa o controller da entidade User
// implementa o endpoint "/users"
@RestController
@RequestMapping(value = "/users")
public class UserResource {

	// injetando o service da entidade User
	@Autowired
	private UserService service;

	// método que retorna todos os registros
	// acessível via método GET
	@GetMapping
	public ResponseEntity<List<User>> findAll() {

		// obtendo a lista de registros
		List<User> list = this.service.findAll();

		// retorna a resposta com status OK e registros no body
		return ResponseEntity.ok().body(list);
	}

	// método que retorna o registro do id selecionado
	// acessível via método GET, adicionando o parâmetro /id
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {

		// obtendo o registro
		User obj = this.service.findById(id);

		// retorna a resposta com status OK e registros no body
		return ResponseEntity.ok().body(obj);
	}

}
