package com.silviotmalmeida.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silviotmalmeida.app.entities.Product;
import com.silviotmalmeida.app.repositories.ProductRepository;

// classe de serviço que realiza a comunicação entre o ProductResource e o ProductRepository
// registrando a classe como service
@Service
public class ProductService {

	// injetando o repository da entidade Product
	@Autowired
	private ProductRepository repository;

	// método que retorna todos os registros
	public List<Product> findAll() {
		return this.repository.findAll();
	}

	// método que retorna o registro do id selecionado
	public Product findById(Long id) {

		// obtendo o registro
		Optional<Product> obj = this.repository.findById(id);

		// retorna o registro, se existir
		return obj.get();
	}

}
