public class Sistema {
    public static void main(String args[]){
        Biblioteca biblioteca = new Biblioteca();

        Cliente cliente = new Cliente("Joao", 1234);
        biblioteca.adicionarCliente(cliente);

        Comum livro = new Comum("Cracking the code interview", "Gayle Laakmann");
        biblioteca.adicionarLivro(livro);

        Raro livro2 = new Raro("Sim", "Sim", "Sim");
        biblioteca.adicionarLivro(livro2);

        System.out.println(biblioteca.clientes.toString());

        Livro encontrado = biblioteca.buscaTitulo("Cracking the code interview");
        System.out.println(encontrado.toString());

        System.out.println(biblioteca.emprestarLivro("Cracking the code interview", cliente));

        System.out.println(biblioteca.verificarDisponibilidade("Cracking the code interview"));

        System.out.println(biblioteca.devolverLivro("Cracking the code interview"));

        
        System.out.println(biblioteca.verificarDisponibilidade("Cracking the code interview"));
    }
}
