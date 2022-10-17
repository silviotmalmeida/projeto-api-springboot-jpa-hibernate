package com.silviotmalmeida.app.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silviotmalmeida.app.entities.Product;
import com.silviotmalmeida.app.services.ProductService;

// classe que implementa o controller da entidade Product
// implementa o endpoint "/categories"
@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	// injetando o service da entidade Product
	@Autowired
	private ProductService service;

	// método que retorna todos os registros
	// acessível via método GET
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {

		// obtendo a lista de registros
		List<Product> list = this.service.findAll();

		// retorna a resposta com status OK e registros no body
		return ResponseEntity.ok().body(list);
	}

	// método que retorna o registro do id selecionado
	// acessível via método GET, adicionando o parâmetro /id
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {

		// obtendo o registro
		Product obj = this.service.findById(id);

		// retorna a resposta com status OK e registros no body
		return ResponseEntity.ok().body(obj);
	}

}
