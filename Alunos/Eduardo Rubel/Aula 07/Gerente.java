
class Gerente extends Funcionario {
    public Gerente(String nome, int idade, Loja loja, Endereco endereco, double salarioBase) {
        super(nome, idade, loja, endereco, salarioBase);
    }

    public void apresentarSe() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja.getNomeFantasia());
    }
}
