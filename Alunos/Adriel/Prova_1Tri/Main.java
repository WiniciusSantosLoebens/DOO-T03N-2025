import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Cliente cliente1 = new Cliente("001", "João Silva");
        biblioteca.cadastrarCliente(cliente1);

        Livro livroComum = new LivroComum("O Pequeno Principe", "Antoine de Saint-Exupéry");
        Livro livroRaro = new LivroRaro("Primeira Edição de Dom Quixote", "Miguel de Cervantes", 
                                        "Edição original de 1605");

        biblioteca.cadastrarLivro(livroComum);
        biblioteca.cadastrarLivro(livroRaro);

        boolean emprestimo1 = biblioteca.realizarEmprestimo(cliente1, livroComum, LocalDate.now());
        System.out.println("Empréstimo livro comum: " + (emprestimo1 ? "Sucesso" : "Falha"));

        boolean emprestimo2 = biblioteca.realizarEmprestimo(cliente1, livroRaro, LocalDate.now());
        System.out.println("Empréstimo livro raro: " + (emprestimo2 ? "Sucesso" : "Falha (esperado)"));

        if (emprestimo1) {
            double multa = biblioteca.realizarDevolucao(livroComum, LocalDate.now().plusDays(10));
            System.out.println("Multa por atraso: R$" + multa);
        }
    }
}
