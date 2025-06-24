import java.util.Scanner;

public class Usuario {
    private String nome;
    private String apelido;

    public Usuario(String nome, String apelido) {
        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Usuário Padrão";
            System.out.println("Atenção: Nome do usuário inválido. Definido como 'Usuário Padrão'.");
        } else {
            this.nome = nome.trim();
        }

        if (apelido == null || apelido.trim().isEmpty()) {
            this.apelido = this.nome; // Usa o nome como apelido se nenhum for dado
            System.out.println("Apelido não fornecido. Usando o nome como apelido.");
        } else {
            this.apelido = apelido.trim();
        }
    }

    // Sobrecarga de construtor para caso o apelido não seja passado inicialmente
    public Usuario(String nome) {
        this(nome, nome); // Chama o construtor principal passando o nome como apelido também
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    // Setters
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        } else {
            System.out.println("Erro: O nome não pode ser vazio.");
        }
    }

    public void setApelido(String apelido) {
        if (apelido != null && !apelido.trim().isEmpty()) {
            this.apelido = apelido.trim();
        } else {
            // Decida o que fazer se nao tiver apelido
            System.out.println("Erro: O apelido não pode ser vazio. Apelido atual mantido.");
        }
    }

    // Método para cadastro de usuario
    public static Usuario cadastrarUsuario(Scanner scan) {
        System.out.println("Bem-vindo ao sistema de acompanhamento de séries!");
        System.out.println("Qual é o seu nome completo?");
        String nomeCompleto = scan.nextLine();

        System.out.println("Como você gostaria de ser chamado (apelido)? (Deixe em branco para usar seu nome)");
        String apelidoInput = scan.nextLine();

        // Se o usuário deixar o apelido em branco, usa o nome como apelido
        String apelidoFinal = apelidoInput.trim().isEmpty() ? nomeCompleto : apelidoInput;

        System.out.println("Cadastro efetuado!");
        return new Usuario(nomeCompleto, apelidoFinal);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Apelido: " + apelido;
    }
}
