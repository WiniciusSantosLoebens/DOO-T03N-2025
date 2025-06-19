import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ativ11 {
    public static void main(String[] args) {
        //Exercicio 1
        Numeros_Par_Q1();
        System.out.println("\n");
        //Exercicio2
        Nomes_Maiusc_Q2();
        System.out.println("\n");
        //Exercicio3
        Cont_Palavra_Q3();
        System.out.println("\n");
        //Exercicio4
        Produt_preço_Q4();
        System.out.println("\n");
        //Exercicio5
        Produt_Soma_Q5();
        System.out.println("\n");
        //Exercicio6
        Ordem_Tamanho_Q6();

    }

    public static void Numeros_Par_Q1() {
        List<Integer> Numerar = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> NumerosPar = Numerar.stream()
                .filter(numeros -> numeros % 2 == 0)
                .collect(Collectors.toList());
        NumerosPar.forEach(System.out::println);
    }

    public static void Nomes_Maiusc_Q2() {
        List<String> Nomes = List.of("roberto", "josé", "caio", "vinicius");
        List<String> Nomes_Maiusculos = Nomes.stream()
                .map(c -> c.toUpperCase())
                .collect(Collectors.toList());
        Nomes_Maiusculos.forEach(System.out::println);
    }

    public static void Cont_Palavra_Q3() {
        List<String> Nomi = List.of("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras = Nomi.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));

        contagemPalavras.forEach((palavra, contagem) ->
                System.out.println(palavra + " : " + contagem));
    }

    public static void Produt_preço_Q4(){
        Produto produto1 = new Produto("computador", 1000.0);
        Produto produto2 = new Produto("celular", 500.0);
        Produto produto3 = new Produto("mesa", 80.0);
        Produto produto4 = new Produto("pilha", 10.0);
        List<Produto> produt = List.of(produto1,produto2,produto3,produto4);
        List<Produto> Valores_100 = produt.stream().
                filter(c -> c.getPreço() > 100.0)
                .collect(Collectors.toList());
        Valores_100.forEach(System.out::println);
    }

    public static void Produt_Soma_Q5(){
        Produto produto1 = new Produto("computador", 1000.0);
        Produto produto2 = new Produto("celular", 500.0);
        Produto produto3 = new Produto("mesa", 80.0);
        Produto produto4 = new Produto("pilha", 10.0);
        List<Produto> produt = List.of(produto1,produto2,produto3,produto4);
        Double v = 0.0;
        v = produt.stream().mapToDouble(Produto::getPreço).sum();
System.out.println("Total dos preços:"+v);
    }

    public static void Ordem_Tamanho_Q6(){
List<String> Palavras = List.of("Java", "Python", "C", "JavaScript", "Ruby");
List<String> Ordenadar = Palavras.stream()
        .sorted(Comparator.comparing(String::length))
        .collect(Collectors.toList());
Ordenadar.forEach(System.out::println);
    }

    }

        class Produto{
            private String nome;
            private Double preço;

            public Produto(String produto, Double valor) {
                this.nome = produto;
                this.preço = valor;
            }

            public String getProduto() {
                return nome;
            }

            public void setProduto(String produto) {
                this.nome = produto;
            }

            public Double getPreço() {
                return preço;
            }

            public void setPreço(Double valor) {
                this.preço = valor;
            }

            @Override
            public String toString() {
                return "Produto{" +
                        "produto='" + nome + '\'' +
                        ", valor=" + preço +
                        '}';
            }
        }






