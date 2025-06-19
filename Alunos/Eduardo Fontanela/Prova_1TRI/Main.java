public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.cadastrarCliente("João");
        biblioteca.cadastrarCliente("Matilde");

        biblioteca.cadastrarLivro("Cracking the Code Interview", "Gayle Laakmann", false, null);
        biblioteca.cadastrarLivro("O Codex Gigas", "Desconhecido", true, "Livro antigo escrito à mão no século XIII");

        Livro livro1 = biblioteca.buscarPorTitulo("Cracking the Code Interview");
        System.out.println("Encontrado: " + livro1.getTitulo());

        Livro livro2 = biblioteca.buscarPorAutor("Gayle Laakmann");
        System.out.println("Encontrado por autor: " + livro2.getTitulo());

        if (biblioteca.emprestarLivro("Cracking the Code Interview")) {
            System.out.println("Livro emprestado com sucesso.");
        } else {
            System.out.println("Não foi possível emprestar o livro.");
        }

        if (!biblioteca.emprestarLivro("O Codex Gigas")) {
            System.out.println("Livro raro não pode ser emprestado.");
        }

        if (!biblioteca.estaDisponivel("Cracking the Code Interview")) {
            System.out.println("Livro indisponível no momento.");
        }

        if (biblioteca.devolverLivro("Cracking the Code Interview")) {
            System.out.println("Livro devolvido com sucesso.");
        }
    }
}
