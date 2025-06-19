package classes;

public class Gerente  extends Pessoa{

	
	
	Endereço endereço;
	Loja loja;
	double salarioBase;
	double[] salarioRecebido = {5000.0 ,5000.0,5000.0};
	
	
	
	public Gerente(String nome, int idade, Loja loja, double salarioBase) {
		
	    super(nome, idade, null); 
	    this.loja = loja;
	    this.salarioBase = salarioBase;
	}
		

   


	public void apresentarse() {
    	
        System.out.println("Nome: " + nome +  ", Idade: " + idade + ", Loja: " + loja.nomeFantasia);
       
    }

    public double calcularMedia() {
    	
        double soma = 0;
        
        for (double valor : salarioRecebido) {
            soma += valor;
        }
        
        return soma / salarioRecebido.length;
    }

    public double calcularBonus() {
    	
        return salarioBase * 0.35;
 
    }
}
	
	
	
	
	
	
	

