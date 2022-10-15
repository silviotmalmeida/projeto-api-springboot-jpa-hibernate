package com.silviotmalmeida.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silviotmalmeida.app.entities.Order;
import com.silviotmalmeida.app.repositories.OrderRepository;

// classe de serviço que realiza a comunicação entre o OrderResource e o OrderRepository
// registrando a classe como service
@Service
public class OrderService {

	// injetando o repository da entidade Order
	@Autowired
	private OrderRepository repository;

	// método que retorna todos os registros
	public List<Order> findAll() {
		return this.repository.findAll();
	}

	// método que retorna o registro do id selecionado
	public Order findById(Long id) {

		// obtendo o registro
		Optional<Order> obj = this.repository.findById(id);

		// retorna o registro, se existir
		return obj.get();
	}

}
