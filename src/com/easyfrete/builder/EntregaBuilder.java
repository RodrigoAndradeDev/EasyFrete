package com.easyfrete.builder;

import com.easyfrete.enums.TipoEntrega;
import com.easyfrete.model.Cliente;
import com.easyfrete.model.Endereco;
import com.easyfrete.model.Entrega;

public class EntregaBuilder {

    private Cliente cliente;
    private Endereco origem;
    private Endereco destino;
    private double peso;
    private double distancia;
    private TipoEntrega tipo;

    public EntregaBuilder cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public EntregaBuilder origem(Endereco origem) {
        this.origem = origem;
        return this;
    }

    public EntregaBuilder destino(Endereco destino) {
        this.destino = destino;
        return this;
    }

    public EntregaBuilder peso(double peso) {
        this.peso = peso;
        return this;
    }

    public EntregaBuilder distancia(double distancia) {
        this.distancia = distancia;
        return this;
    }

    public EntregaBuilder tipo(TipoEntrega tipo) {
        this.tipo = tipo;
        return this;
    }

    public Entrega build() {

        if (cliente == null || origem == null ||
            destino == null || tipo == null) {

            throw new IllegalStateException(
                "Cliente, origem, destino e tipo são obrigatórios."
            );
        }

        if (peso <= 0 || distancia <= 0) {
            throw new IllegalStateException(
                "Peso e distância devem ser positivos."
            );
        }

        return new Entrega(
            cliente,
            origem,
            destino,
            peso,
            distancia,
            tipo
        );
    }
}