package com.silviotmalmeida.app.entities;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

// classe que representa uma entidade Payment
// esta é uma classe dependente de Order
// é serializable para permitir operações de IO
// foi incluída a anotação de identificação como entidade para o JPA
// foi incluída a anotação para mapeamento da tabela tb_payment
@Entity
@Table(name = "tb_payment")
public class Payment implements Serializable {

    // atributo serial (obrigatório em serializables)
    private static final long serialVersionUID = 1L;

    // declaração dos atributos
    //// definindo o id como chave primária autoincrementável
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment;

    // associação 1x1 com a entidade Order
    // a anotação @MapsId deve ser colocada na classe dependente, que terá o mesmo
    // id da classe principal
    // a anotação JsonIgnore serve informar que esta entidade não irá apresentar os
    // dados desta associação para evitar loop infinito na resposta e deve ser
    // colocado em um dos lados das associações
    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;

    // construtor vazio (necessário para o framework)
    public Payment() {

    }

    // construtor
    public Payment(Long id, Instant moment, Order order) {
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    // início dos getters e setters
    // ------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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
        Payment other = (Payment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
