// Vendedor.java
//public class Vendedor extends Pessoa {
    private String loja;
    private int salarioBase;

    public Vendedor(String nome, int idade, String cidade, String bairro, String rua, String loja, int salarioBase) {
        super(nome, idade, cidade, bairro, rua);
        this.loja = loja;
        this.salarioBase = salarioBase;
    }

    @Override
    public void apresentarSe() {
        super.apresentarSe();
        System.out.println("Sou vendedor da loja: " + loja);
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}