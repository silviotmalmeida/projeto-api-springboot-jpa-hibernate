package com.silviotmalmeida.app.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

		// retorna a resposta com status 200 e registros no body
		return ResponseEntity.ok().body(list);
	}

	// método que retorna o registro do id selecionado
	// acessível via método GET, adicionando o parâmetro /id
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {

		// obtendo o registro
		User obj = this.service.findById(id);

		// retorna a resposta com status 200 e registros no body
		return ResponseEntity.ok().body(obj);
	}

	// método que insere um registro no BD
	// acessível via método POST, com a passagem dos atributos pelo body
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {

		// inserindo o registro
		obj = this.service.insert(obj);

		// construindo a location do novo registro
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		// retorna a resposta com status 201, location no header e registros no body
		return ResponseEntity.created(uri).body(obj);
	}

	// método que remove um registro no BD
	// acessível via método DELETE, adicionando o parâmetro /id
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		// removendo o registro
		this.service.delete(id);

		// retorna a resposta com status 204
		return ResponseEntity.noContent().build();
	}

	// método que edita um registro no BD
	// acessível via método PUT, adicionando o parâmetro /id
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {

		// editando o registro
		obj = this.service.update(id, obj);

		// retorna a resposta com status 200 e registros no body
		return ResponseEntity.ok().body(obj);
	}
}
