package com.silviotmalmeida.app.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

// classe que representa o erro padrão a ser enviado na camada de recursos
public class StandardError implements Serializable {

    // atributo serial (obrigatório em serializables)
    private static final long serialVersionUID = 1L;

    // declaração dos atributos
    // definindo o formato ISO8601 como padrão
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;
    
    private Integer status;
    private String error;
    private String message;
    private String path;

    // construtor vazio (necessário para o framework)
    public StandardError() {

    }

    // construtor
    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // início dos getters e setters
    // ------------------------------------------------------------------
    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    // fim dos getters e setters
    // ------------------------------------------------------------------

}
