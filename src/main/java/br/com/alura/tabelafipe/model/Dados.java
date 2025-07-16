package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Dados(
    @JsonAlias("code") String codigo,
    @JsonAlias("name") String nome) {
}
