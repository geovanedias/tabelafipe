package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
    @JsonAlias("price") String valor,
    @JsonAlias("brand") String marca,
    @JsonAlias("model") String modelo,
    @JsonAlias("modelYear") Integer ano,
    @JsonAlias("fuel") String combustivel
) {
    @Override
    public String toString() {
        return "\n" +
            "Marca: " + marca + "\n" +
            "Modelo: " + modelo + "\n" +
            "Ano: " + ano + "\n" +
            "Combustível: " + combustivel +
            "Valor: " + valor + "\n";
    }
}
