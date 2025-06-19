;
public class Gerente extends Funcionario {;
 public Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
 super(nome, idade, loja, endereco, salarioBase);
 }
 
 public double calcularBonus() {;
 return salarioBase * 0.35;
 }
}
