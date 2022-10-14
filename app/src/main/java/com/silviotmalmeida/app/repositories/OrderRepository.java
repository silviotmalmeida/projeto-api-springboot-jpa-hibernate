package com.silviotmalmeida.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silviotmalmeida.app.entities.Order;

// classe responsável por executar as operações da entidade Order no BD
// as operações básicas já estão implementadas na superclasse JpaRepository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
