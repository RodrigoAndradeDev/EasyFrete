package com.easyfrete.builder;

import com.easyfrete.model.Endereco;

public class EnderecoBuilder {

    private String rua;
    private String cidade;
    private String estado;
    private String cep;

    public EnderecoBuilder rua(String rua) {
        this.rua = rua;
        return this;
    }

    public EnderecoBuilder cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public EnderecoBuilder estado(String estado) {
        this.estado = estado;
        return this;
    }

    public EnderecoBuilder cep(String cep) {
        this.cep = cep;
        return this;
    }

    public Endereco build() {

        if (rua == null || cidade == null || estado == null || cep == null) {
            throw new IllegalStateException("Rua, cidade, estado e CEP são obrigatórios.");
        }

        if (rua.isBlank() || cidade.isBlank() || estado.isBlank() || cep.isBlank()) {
          throw new IllegalStateException("Os campos do endereço não podem estar vazios.");
        }

        return new Endereco(
            rua,
            cidade,
            estado,
            cep
        );
    }
}