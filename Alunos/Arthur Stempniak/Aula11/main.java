import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Classe Produto para Atv4 e Atv5
//Classe foi feita aqui pois o senhor especificou que todas as atividades devem estar no arquivo Main.
class Produto {
    private String nome;
    private double preco;

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
        return "Produto{" +
               "nome='" + nome + '\'' +
               ", preco=" + preco +
               '}';
    }
}

public class main {  // Corrigido de 'main' para 'Main'

    public static void main(String[] args) {

        //ATV1
        System.out.println("----- ATV1 -----");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<Integer> numerosPares = numeros.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println("Lista original: " + numeros);
        System.out.println("Números pares: " + numerosPares);
        System.out.println("----------------\n");

        //ATV2
        System.out.println("----- ATV2 -----");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Nomes originais: " + nomes);
        System.out.println("Nomes maiúsculos: " + nomesMaiusculos);
        System.out.println("----------------\n");

        //ATV3
        System.out.println("----- ATV3 -----");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sabado", "se", "quarta", "sabado", "hoje", "se");
        Map<String, Long> contagemPalavras = palavras.stream().collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));
        System.out.println("Lista de palavras: " + palavras);
        System.out.println("Contagem de palavras: " + contagemPalavras);
        System.out.println("----------------\n");

         // ATV4
        System.out.println("----- ATV4 -----");

        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook Gamer", 5500.00),
                new Produto("Mouse sem fio", 89.90),
                new Produto("Teclado Mecânico", 350.00),
                new Produto("Monitor 4K", 1800.00),
                new Produto("Webcam HD", 75.50)
        );

        System.out.println("\nLista de todos os produtos:");
        produtos.forEach(p -> System.out.println(p));

        // Filtrando os produtos com preço maior que 100
        List<Produto> produtosCaros = produtos.stream()
                                             .filter(p -> p.getPreco() > 100.00)
                                             .collect(Collectors.toList());

        System.out.println("\nProdutos com preço maior que R$ 100,00:");
        if (produtosCaros.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            produtosCaros.forEach(p -> System.out.println(p));
        }
        System.out.println("----------------\n");


        // ATV5
        System.out.println("----- ATV5 -----");

        double somaTotalProdutos = produtos.stream()
                                         .mapToDouble(Produto::getPreco)
                                         .sum();

        System.out.println("\nLista de produtos:");
        produtos.forEach(p -> System.out.println(p));

        System.out.println("\nSoma total do valor dos produtos: R$ " + String.format("%.2f", somaTotalProdutos));
        System.out.println("----------------\n");

        //ATV6
        System.out.println("----- ATV6 -----");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                                                    .sorted(Comparator.comparingInt(String::length))
                                                    .collect(Collectors.toList());
        System.out.println("Lista original de linguagens: " + linguagens);
        System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);
        System.out.println("----------------\n");
    }
}
