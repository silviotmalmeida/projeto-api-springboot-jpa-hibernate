package com.silviotmalmeida.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.silviotmalmeida.app.entities.User;

// classe responsável por executar as operações da entidade User no BD
// as operações básicas já estão implementadas na superclasse JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {

}
