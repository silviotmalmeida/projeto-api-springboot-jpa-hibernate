package com.silviotmalmeida.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silviotmalmeida.app.entities.Category;

// classe responsável por executar as operações da entidade Category no BD
// as operações básicas já estão implementadas na superclasse JpaRepository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
