package com.easyfrete.builder;

import java.util.UUID;

import com.easyfrete.model.Cliente;

public class ClienteBuilder {

    private UUID id;
    private String nome;
    private String contato;

    public ClienteBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public ClienteBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteBuilder contato(String contato) {
        this.contato = contato;
        return this;
    }

    public Cliente build() {

        if (nome == null || contato == null) throw new IllegalStateException("Nome e contato são obrigatórios.");
        if (nome.isBlank() || contato.isBlank()) throw new IllegalStateException("Nome e contato não podem estar vazios.");
        if (id == null) id = UUID.randomUUID();

        return new Cliente(
            id,
            nome,
            contato
        );
    }
}