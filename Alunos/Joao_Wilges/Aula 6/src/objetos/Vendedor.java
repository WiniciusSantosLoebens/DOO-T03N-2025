package objetos;

public class Vendedor {
    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase = 0;
    private double salarioRecebido1 = 0;
    private double salarioRecebido2 = 0;
    private double salarioRecebido3 = 0;
    private int qtd_salario = 3;
    private int contador_salario = 0;

    public Vendedor(String nome, Loja loja, int idade, String cidade, String rua, String bairro, double salarioBase) {
        this.nome = nome;
        this.loja = loja;
        this.idade = idade;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.salarioBase = salarioBase;
    }
    public String getNome() {
        return nome;
    }

    public Vendedor(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }
    public double calcularMedia() {
        return (salarioRecebido1 + salarioRecebido2 + salarioRecebido3) / qtd_salario;
    }
    public double calcularBonus(){
        return salarioBase * 0.2;
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