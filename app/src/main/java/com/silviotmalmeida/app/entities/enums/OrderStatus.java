package com.silviotmalmeida.app.entities.enums;

// classe que define o tipo enumerado do status do pedido
public enum OrderStatus {

    // declarando os valores e explicitando os referidos índices numéricos
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    // inserindo atributos e construtores padrão para permitir explicitar os índices
    // numéricos
    // -----------------------------------------------------------------
    private int code;

    private OrderStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    // -----------------------------------------------------------------

    // método responsável por interpretar o índice númerico do status do pedido
    public static OrderStatus valueOf(int code) {

        // iterando sobre os valores dos status possíveis
        for (OrderStatus value : OrderStatus.values()) {

            // se houver correspondência com o argumento, retorna o enum
            if (value.getCode() == code) {
                return value;
            }
        }
        // senão lança uma exceção
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }

}
