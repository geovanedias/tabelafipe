package br.com.alura.tabelafipe.main;

import br.com.alura.tabelafipe.service.ConsumoAPI;

import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoAPI consumo = new ConsumoAPI();

    private static final String URL_BASE = "https://fipe.parallelum.com.br/api/v2/";

    public void exibeMenu() {
        var menu = """
            *** OPÇÕES ***
            
                Carro
                Moto
                Caminhão
            
            Digite uma das opções para consultar:
            """;

        System.out.println(menu);
        var opcao = scanner.nextLine();

        String endereco;

        if (opcao.toLowerCase().contains("carr")) {
            endereco = URL_BASE + "cars/brands/";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motorcycles/brands/";
        } else {
            endereco = URL_BASE + "truks/brands/";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);

        scanner.close();
    }
}
