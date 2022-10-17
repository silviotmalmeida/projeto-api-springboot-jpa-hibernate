package com.silviotmalmeida.app.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silviotmalmeida.app.entities.Category;
import com.silviotmalmeida.app.services.CategoryService;

// classe que implementa o controller da entidade Category
// implementa o endpoint "/categories"
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	// injetando o service da entidade Category
	@Autowired
	private CategoryService service;

	// método que retorna todos os registros
	// acessível via método GET
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {

		// obtendo a lista de registros
		List<Category> list = this.service.findAll();

		// retorna a resposta com status OK e registros no body
		return ResponseEntity.ok().body(list);
	}

	// método que retorna o registro do id selecionado
	// acessível via método GET, adicionando o parâmetro /id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {

		// obtendo o registro
		Category obj = this.service.findById(id);

		// retorna a resposta com status OK e registros no body
		return ResponseEntity.ok().body(obj);
	}

}
