package br.com.alura.tabelafipe.main;

import br.com.alura.tabelafipe.model.Dados;
import br.com.alura.tabelafipe.service.ConsumoAPI;
import br.com.alura.tabelafipe.service.ConverteDados;

import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoAPI consumo = new ConsumoAPI();
    private final ConverteDados conversor = new ConverteDados();

    private static final String URL_BASE = "https://fipe.parallelum.com.br/api/v2/";

    public void exibeMenu() {
        var menu = """
            *** OPÇÕES ***
            
              1-  Carro
              2-  Moto
              3-  Caminhão
            
            Digite uma das opções para consultar:
            """;

        System.out.println(menu);
        var opcao = scanner.nextLine();

        String endereco;

        if (opcao.toLowerCase().contains("carr")) {     // TODO: trocar para usar número ao invés de digitar
            endereco = URL_BASE + "cars/brands/";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motorcycles/brands/";
        } else {
            endereco = URL_BASE + "truks/brands/";
        }

        var json = consumo.obterDados(endereco);
        System.out.println(json);

        var marcas = conversor.obterLista(json, Dados.class);

        marcas.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var codigoMarca = scanner.nextInt();

        scanner.close();
    }
}
