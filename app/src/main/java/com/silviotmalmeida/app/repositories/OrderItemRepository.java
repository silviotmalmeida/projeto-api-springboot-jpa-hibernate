package com.silviotmalmeida.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silviotmalmeida.app.entities.OrderItem;

// classe responsável por executar as operações da entidade OrderItem no BD
// as operações básicas já estão implementadas na superclasse JpaRepository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
