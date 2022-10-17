package com.silviotmalmeida.app.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

// classe que representa uma entidade Category
// é serializable para permitir operações de IO
// foi incluída a anotação de identificação como entidade para o JPA
// foi incluída a anotação para mapeamento da tabela tb_category
@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    // atributo serial (obrigatório em serializables)
    private static final long serialVersionUID = 1L;

    // declaração dos atributos
    //// definindo o id como chave primária autoincrementável
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // associação nxn com a entidade Product
    // este relacionamento só precisa ser implementado em uma das classes e
    // referenciado na outra
    // referenciando a implementação contida em Product
    // a anotação JsonIgnore serve informar que esta entidade não irá apresentar os
	// dados desta associação para evitar loop infinito na resposta e deve ser
	// colocado em um dos lados das associações
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    // construtor vazio (necessário para o framework)
    public Category() {

    }

    // construtor
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public Set<Product> getProducts() {
        return products;
    }
    // fim dos getters e setters
    // ------------------------------------------------------------------

    // hashcode e equals para permitir comparação de objetos
    // ------------------------------------------------------------------
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    // ------------------------------------------------------------------

}
