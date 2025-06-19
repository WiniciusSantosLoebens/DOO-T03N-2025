public class Livros {
    String nomeLivro;
    String nomeAutor;
    boolean emEmprestimo = false;
    int diasEmprestado = 0;

    public Livros(String nomeLivro, String nomeAutor) {
        this.nomeLivro = nomeLivro;
        this.nomeAutor = nomeAutor;
    }

    public void registrarEmprestimo() {
        if (!emEmprestimo) {
            emEmprestimo = true;
            diasEmprestado = 0;
            System.out.println("Livro emprestado com sucesso.");
        } else {
            System.out.println("Livro já está emprestado.");
        }
    }

    public void registrarDevolucao() {
        if (emEmprestimo) {
            emEmprestimo = false;
            if (diasEmprestado > 7) {
                double multa = (diasEmprestado - 7) * 3.5;
                System.out.println("Multa por atraso: R$ " + multa);
            } else {
                System.out.println("Livro devolvido sem multa.");
            }
        } else {
            System.out.println("Esse livro não estava emprestado.");
        }
    }
}
