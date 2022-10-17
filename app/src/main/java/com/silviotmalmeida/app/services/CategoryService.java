package com.silviotmalmeida.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silviotmalmeida.app.entities.Category;
import com.silviotmalmeida.app.repositories.CategoryRepository;

// classe de serviço que realiza a comunicação entre o CategoryResource e o CategoryRepository
// registrando a classe como service
@Service
public class CategoryService {

	// injetando o repository da entidade Category
	@Autowired
	private CategoryRepository repository;

	// método que retorna todos os registros
	public List<Category> findAll() {
		return this.repository.findAll();
	}

	// método que retorna o registro do id selecionado
	public Category findById(Long id) {

		// obtendo o registro
		Optional<Category> obj = this.repository.findById(id);

		// retorna o registro, se existir
		return obj.get();
	}

}
