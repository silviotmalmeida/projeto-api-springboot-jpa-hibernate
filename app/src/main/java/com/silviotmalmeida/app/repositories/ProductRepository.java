package com.silviotmalmeida.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silviotmalmeida.app.entities.Product;

// classe responsável por executar as operações da entidade Product no BD
// as operações básicas já estão implementadas na superclasse JpaRepository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
