package com.silviotmalmeida.app.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.silviotmalmeida.app.entities.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// classe que representa a tabela de associação entre pedido e produto
// foi construída pois possui dados extras da associação
// é serializable para permitir operações de IO
// foi incluída a anotação de identificação como entidade para o JPA
// foi incluída a anotação para mapeamento da tabela tb_category
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    // atributo serial (obrigatório em serializables)
    private static final long serialVersionUID = 1L;

    // declaração dos atributos
    // atributo que vai representar a chave primária composta da tabela
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    // construtor vazio (necessário para o framework)
    public OrderItem() {

    }

    // construtor
    // observar que o id é construído a partir do pedido e do produto
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        this.id.setOrder(order);
        this.id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    // início dos getters e setters
    // ------------------------------------------------------------------

    // a anotação JsonIgnore serve informar que esta entidade não irá apresentar os
    // dados desta associação para evitar loop infinito na resposta e deve ser
    // colocado em um dos lados das associações
    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        this.id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        this.id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
        OrderItem other = (OrderItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    // ------------------------------------------------------------------

    // métodos adicionais
    // ------------------------------------------------------------------

    // método que retorna o valor total deste item de pedido
    public Double getSubTotal() {
        return this.price * this.quantity;
    }

}
