//CadastroDeLivros.java
public class CadastroDeLivros {
    String nome;    
    String autor;
    Boolean tipoLivro; // true = comum, false = raro
    Boolean disponibilidadeLivro; // true = disponível, false = não disponível
    int anoPublicacao;
    
    public CadastroDeLivros(String nome, String autor, Boolean tipoLivro, Boolean disponibilidadeLivro, int anoPublicacao) {
        this.nome = nome;
        this.autor = autor;
        this.tipoLivro = tipoLivro;
        this.disponibilidadeLivro = disponibilidadeLivro;
        this.anoPublicacao = anoPublicacao;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Autor: " + autor);
        System.out.println("Tipo de Livro: " + (tipoLivro ? "Comum" : "Raro"));
        System.out.println("Disponibilidade do Livro: " + (disponibilidadeLivro ? "Disponível" : "Não Disponível"));
        System.out.println("Ano de Publicação: " + anoPublicacao);
    }

    public static void main(String[] args) {

        CadastroDeLivros livro = new CadastroDeLivros("O Senhor dos Anéis", "J.R.R. Tolkien", true, true, 1954);
        livro.exibirDados(); 

        class CadastroCliente {
            String nome;
            String endereco;
            String telefone;
            String email;
            int idade;
        
            public CadastroCliente(String nome, String endereco, String telefone, String email, int idade) {
                this.nome = nome;
                this.endereco = endereco;
                this.telefone = telefone;
                this.email = email;
                this.idade = idade;
            }
        
            public void exibirDados() {
                System.out.println("Nome: " + nome);
                System.out.println("Endereço: " + endereco);
                System.out.println("Telefone: " + telefone);
                System.out.println("Email: " + email);
                System.out.println("Idade: " + idade);
            }
        }
        
        CadastroCliente cliente = new CadastroCliente("Matilda", "Rua das Flores, 123", "1234-5678", "matilda@email.com", 32);
        cliente.exibirDados();
    }
}