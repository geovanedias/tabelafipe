package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Dados(
    @JsonAlias("name") String nome,
    @JsonAlias("code") String codigo) {
}
