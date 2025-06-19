public class Cliente {
        String nome;
        int idade;
        Endereco endereco;
        Loja loja;

        public Cliente(String nome, int idade, Endereco endereco, Loja loja) {
            this.nome = nome;
            this.idade = idade;
            this.endereco = endereco;
            this.loja = loja;
        }

        public void apresentarse() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            endereco.apresentarLogradouro();
            System.out.println("---------------------");
        }
    }