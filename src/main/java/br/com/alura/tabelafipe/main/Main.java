package br.com.alura.tabelafipe.main;

import br.com.alura.tabelafipe.model.Dados;
import br.com.alura.tabelafipe.model.Veiculo;
import br.com.alura.tabelafipe.service.ConsumoAPI;
import br.com.alura.tabelafipe.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

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

        if (opcao.toLowerCase().contains("car")) {
            endereco = URL_BASE + "cars/brands/";
        } else if (opcao.toLowerCase().contains("mot")) {
            endereco = URL_BASE + "motorcycles/brands/";
        } else {
            endereco = URL_BASE + "trucks/brands/";
        }

        AtomicReference<String> json = new AtomicReference<>(consumo.obterDados(endereco));
        System.out.println(json.get());

        var marcas = conversor.obterLista(json.get(), Dados.class);

        marcas.stream()
                .sorted(Comparator.comparing(Dados::nome))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta: ");
        var codigoMarca = scanner.nextInt();
        scanner.nextLine();

        endereco = endereco + codigoMarca + "/models";
        json.set(consumo.obterDados(endereco));

        var modeloLista = conversor.obterLista(json.get(), Dados.class);

        System.out.println("\nModelos dessa marca:");
        modeloLista.forEach(System.out::println);

        System.out.println("\nDigite o nome do modelo que gostaria de filtrar");
        var nomeVeiculo = scanner.nextLine();

        List<Dados> modelosFiltrados = modeloLista.stream()
                .filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                    .toList();

        System.out.println("\nModelos encontrados");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("\nDigite o código do modelo que gostaria encontrar:");
        var codigoModelo = scanner.nextInt();
        scanner.nextLine();

        endereco = endereco + "/" + codigoModelo + "/years/";

        json.set(consumo.obterDados(endereco));
        List<Dados> anos = conversor.obterLista(json.get(), Dados.class);

        String finalEndereco = endereco;

        anos.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(c -> {
                    String respostaJson = consumo.obterDados(finalEndereco + c.codigo());
                    Veiculo veiculo = conversor.obterDados(respostaJson, Veiculo.class);
                    System.out.println(veiculo);
                });

        scanner.close();
    }
}
