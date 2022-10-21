package com.silviotmalmeida.app.entities.pk;

import java.io.Serializable;

import com.silviotmalmeida.app.entities.Order;
import com.silviotmalmeida.app.entities.Product;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// classe que representa a chave primária composta para a tabela de associação
// entre pedido e produto
@Embeddable
public class OrderItemPK implements Serializable {

    // atributo serial (obrigatório em serializables)
    private static final long serialVersionUID = 1L;

    // declaração dos atributos

    // associação nx1 com a entidade Order
    // definindo o nome da coluna com a chave estrangeira para order_id
    // como trata-se de chave primária composta, o tratamento com @JsonIgnore deverá
    // ser feito no getOrder da classe OrderItem
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // associação nx1 com a entidade Product
    // definindo o nome da coluna com a chave estrangeira para product_id
    // como trata-se de chave primária composta, o tratamento com @JsonIgnore deverá
    // ser feito no getProduct da classe OrderItem
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // não há necessidade de construtores

    // início dos getters e setters
    // ------------------------------------------------------------------
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    // fim dos getters e setters
    // ------------------------------------------------------------------

    // hashcode e equals para permitir comparação de objetos
    // ------------------------------------------------------------------
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((order == null) ? 0 : order.hashCode());
        result = prime * result + ((product == null) ? 0 : product.hashCode());
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
        OrderItemPK other = (OrderItemPK) obj;
        if (order == null) {
            if (other.order != null)
                return false;
        } else if (!order.equals(other.order))
            return false;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }
}
