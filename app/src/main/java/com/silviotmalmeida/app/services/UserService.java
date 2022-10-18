package com.silviotmalmeida.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silviotmalmeida.app.entities.User;
import com.silviotmalmeida.app.repositories.UserRepository;

// classe de serviço que realiza a comunicação entre o UserResource e o UserRepository
// registrando a classe como service
@Service
public class UserService {

	// injetando o repository da entidade User
	@Autowired
	private UserRepository repository;

	// método que retorna todos os registros
	public List<User> findAll() {
		return this.repository.findAll();
	}

	// método que retorna o registro do id selecionado
	public User findById(Long id) {

		// obtendo o registro
		Optional<User> obj = this.repository.findById(id);

		// retorna o registro, se existir
		return obj.get();
	}

	// método que insere um registro no BD
	public User insert(User obj) {

		// retorna o registro, após a inserção
		return this.repository.save(obj);
	}
}
