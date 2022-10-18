package com.silviotmalmeida.app.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// classe que representa uma entidade Product
// é serializable para permitir operações de IO
// foi incluída a anotação de identificação como entidade para o JPA
// foi incluída a anotação para mapeamento da tabela tb_product
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    // atributo serial (obrigatório em serializables)
    private static final long serialVersionUID = 1L;

    // declaração dos atributos
    //// definindo o id como chave primária autoincrementável
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    // associação nxn com a entidade Category
    // este relacionamento só precisa ser implementado em uma das classes e
    // referenciado na outra pois a tabela de associação não tem dados próprios
    // definindo a tabela de associação e os atributos da mesma
    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    // associação 1xn com a entidade OrderItem
    // definindo o nome do atributo do objeto OrderItem a ser considerado na
    // associação
    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    // construtor vazio (necessário para o framework)
    public Product() {

    }

    // construtor
    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    // deverá retornar um conjunto de pedidos a partir dos itens de pedido
    // a anotação JsonIgnore serve informar que esta entidade não irá apresentar os
    // dados desta associação para evitar loop infinito na resposta e deve ser
    // colocado em um dos lados das associações
    @JsonIgnore
    public Set<Order> getOrders() {

        // inicializando o conjunto vazio
        Set<Order> set = new HashSet<>();

        // iterando sobre os itens de pedido
        for (OrderItem x : this.items) {

            // adicionando somente os pedidos
            set.add(x.getOrder());
        }

        // retornando o conjunto de pedidos
        return set;
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
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    // ------------------------------------------------------------------
}
