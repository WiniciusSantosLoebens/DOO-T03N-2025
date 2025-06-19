package objetos;

public class Funcionario extends Pessoa {
    double salarioBase = 0;
    Loja loja;
    private double salarioRecebido1 = 0;
    private double salarioRecebido2 = 0;
    private double salarioRecebido3 = 0;
    private int qtd_salario = 3;
    private int contador_salario = 0;
    public Funcionario(String nome, Loja loja, int idade, String cidade, String rua, String bairro, String complemento, String numero, double salarioBase) {
        super(nome, idade, cidade, rua, bairro, complemento, numero);
    }

    public void apresentarse() {
        System.out.println("Nome: " + this.getNome());
        System.out.println("Idade: " + this.getIdade());
        System.out.println("Loja: " + loja);
    }
    public double calcularMedia() {
        return (salarioRecebido1 + salarioRecebido2 + salarioRecebido3) / qtd_salario;
    }
    public double calcularBonus(Float porcentagem_bonus){
        return salarioBase * porcentagem_bonus;
    }

    public void adicionarSalarioRecebido(double salario){
        switch (contador_salario){
            case 0:
                salarioRecebido1 = salario;
                contador_salario ++;
            case 1:
                salarioRecebido2 = salario;
                contador_salario ++;
            case 2:
                salarioRecebido3 = salario;
                contador_salario = 0;
        }
    }
}
