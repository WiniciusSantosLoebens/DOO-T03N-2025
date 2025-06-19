package Alunos.Braian.Aula11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int opcao = 0;
            while (opcao != 7) {
                exibirMenu();
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> Atividade1.executar();
                    case 2 -> Atividade2.executar();
                    case 3 -> Atividade3.executar();
                    case 4 -> Atividade4.executar();
                    case 5 -> Atividade5.executar();
                    case 6 -> Atividade6.executar();
                    case 7 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opção inválida!");
                }
            }
        }
    }

    // Exibe as opções do menu
    public static void exibirMenu() {
        System.out.println("Digite 1 para: Filtrar números pares");
        System.out.println("Digite 2 para: Converter nomes para maiúsculas");
        System.out.println("Digite 3 para: Contar frequência de palavras");
        System.out.println("Digite 4 para: Filtrar produtos com preço > 100");
        System.out.println("Digite 5 para: Somar preços dos produtos");
        System.out.println("Digite 6 para: Ordenar linguagens por tamanho");
        System.out.println("Digite 7 para: Sair do sistema");
    }

    // ATIVIDADE 1: Filtragem de números pares
    static class Atividade1 {
        public static void executar() {
            List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
            List<Integer> pares = numeros.stream()
                    .filter(n -> n % 2 == 0)
                    .collect(Collectors.toList());
            System.out.println("Números pares: " + pares);
        }
    }

    // ATIVIDADE 2: Transformação para maiúsculas
    static class Atividade2 {
        public static void executar() {
            List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
            List<String> nomesMaiusculos = nomes.stream()
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            System.out.println("Nomes em maiúsculas: " + nomesMaiusculos);
        }
    }

    // ATIVIDADE 3: Contagem de palavras
    static class Atividade3 {
        public static void executar() {
            List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
            Map<String, Long> contagemPalavras = palavras.stream()
                    .collect(Collectors.groupingBy(
                            String::toString,
                            Collectors.counting()
                    ));
            System.out.println("Contagem de palavras: " + contagemPalavras);
        }
    }

    // ATIVIDADE 4: Filtragem de produtos caros
    static class Produto {
        private final String nome;
        private final double preco;

        public Produto(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }

        public String getNome() {
            return nome;
        }

        public double getPreco() {
            return preco;
        }

        @Override
        public String toString() {
            return "Produto{nome='" + nome + "', preco=" + preco + "}";
        }
    }

    static class Atividade4 {
        public static void executar() {
            List<Produto> produtos = Arrays.asList(
                    new Produto("Notebook", 2500.00),
                    new Produto("Mouse", 80.00),
                    new Produto("Teclado", 150.00),
                    new Produto("Monitor", 900.00)
            );
            List<Produto> produtosCaros = produtos.stream()
                    .filter(p -> p.getPreco() > 100.00)
                    .collect(Collectors.toList());
            System.out.println("Produtos com preço > 100: " + produtosCaros);
        }
    }

    // ATIVIDADE 5: Soma dos preços dos produtos
    static class Atividade5 {
        public static void executar() {
            List<Produto> produtos = Arrays.asList(
                    new Produto("Notebook", 2500.00),
                    new Produto("Mouse", 80.00),
                    new Produto("Teclado", 150.00),
                    new Produto("Monitor", 900.00)
            );
            double somaPrecos = produtos.stream()
                    .mapToDouble(Produto::getPreco)
                    .sum();
            System.out.println("Soma dos preços: R$" + somaPrecos);
        }
    }

    // ATIVIDADE 6: Ordenação das linguagens por tamanho
    static class Atividade6 {
        public static void executar() {
            List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
            List<String> linguagensOrdenadas = linguagens.stream()
                    .sorted(Comparator.comparingInt(String::length))
                    .collect(Collectors.toList());
            System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
        }
    }
}
