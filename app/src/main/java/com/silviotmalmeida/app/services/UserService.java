package com.silviotmalmeida.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.silviotmalmeida.app.entities.User;
import com.silviotmalmeida.app.repositories.UserRepository;
import com.silviotmalmeida.app.services.exceptions.DatabaseException;
import com.silviotmalmeida.app.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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

		// se existir, retorna o registro, senão lança uma exceção personalizada
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	// método que insere um registro no BD
	public User insert(User obj) {

		// retorna o registro, após a inserção
		return this.repository.save(obj);
	}

	// método que remove um registro no BD
	public void delete(Long id) {

		// tratando exceções
		try {
			// retorna o registro, após a remoção
			this.repository.deleteById(id);
		}
		// em caso de id não encontrado, lança a exceção personalizada
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		// em caso de id com pedidos, lança a exceção personalizada
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	// método que edita um registro no BD
	public User update(Long id, User obj) {

		// tratando exceções
		try {

			// preparando um objeto monitorado
			// é mais performático do que o findById
			User entity = this.repository.getReferenceById(id);

			// editando os atributos permitidos
			updateData(entity, obj);

			// retorna o registro, após a edição
			return this.repository.save(entity);
		}
		// em caso de id não encontrado, lança a exceção personalizada
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	// método auxiliar para realizar a edição de atributos permitidos de um registro
	private void updateData(User entity, User obj) {

		// editando os atributos permitidos
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
