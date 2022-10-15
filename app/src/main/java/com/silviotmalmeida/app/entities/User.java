package com.silviotmalmeida.app.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// classe que representa uma entidade User
// é serializable para permitir operações de IO
// foi incluída a anotação de identificação como entidade para o JPA
// foi incluída a anotação para mapeamento da tabela tb_user
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

	// atributo serial (obrigatório em serializables)
	private static final long serialVersionUID = 1L;

	// declaração dos atributos
	//// definindo o id como chave primária autoincrementável
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String email;
	private String phone;
	private String password;

	// associação 1xn com a entidade Order
	// definindo o nome do atributo do objeto Order a ser considerado na associação
	// a anotação JsonIgnore serve informar que esta entidade não irá apresentar os
	// dados desta associação para evitar loop infinito na resposta e deve ser
	// colocado em um dos lados das associações
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();

	// construtor vazio (necessário para o framework)
	public User() {

	}

	// construtor
	public User(Long id, String name, String email, String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	// início dos getters e setters
	// ------------------------------------------------------------------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}
	// fim dos getters e setters
	// ------------------------------------------------------------------

	// hashcode e equals para permitir comparação de objetos
	// ------------------------------------------------------------------
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	// ------------------------------------------------------------------

}
