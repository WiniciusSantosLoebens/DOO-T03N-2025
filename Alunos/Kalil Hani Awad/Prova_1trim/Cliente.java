import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    public static Scanner scanner = new Scanner(System.in);
    private String nome;
    private int idade;
    public static ArrayList<Cliente> Persono = new ArrayList<>();

    public Cliente(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        Persono.add(this);
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
    public void Addclie(){
        System.out.println("Nome do cliente");
        String nome = scanner.nextLine();

        System.out.println("Idade do cliente");
        int idade = scanner.nextInt();

        Cliente alguem = new Cliente(nome,idade);
        Persono.add(alguem);
    }
    public void lista_clientes(){
        for(Cliente m : Persono){
            System.out.println(m);
        }
    }
}
/*
Cadastro de clientes.
Cadastro de Livros (devem ser incluídos dois tipos de livros, comuns e raros, livros raros tem uma explicação de porque são raros e não podem ser emprestados).
Buscar livro por titulo, retornando o primeiro registro encontrado. (ex: Cracking the code interview)
Buscar livro por autor, retornando o primeiro registro encontrado. (ex: Gayle Laakmann)
Realizar empréstimo.
Realizar devolução.
Verificar a disponibilidade de empréstimo de um título (Há apenas uma cópia de cada livro na biblioteca).
Na devolução, em caso de atraso após 7 dias, gerar uma multa de 3,50 por dia de atraso.
 */