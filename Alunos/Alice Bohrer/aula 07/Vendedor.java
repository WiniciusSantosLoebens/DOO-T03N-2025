package classes;

public class Vendedor {
	
    String nome;
	int idade;
	Loja loja;
	String cidade;
	String bairro;
	String rua;
	double SalarioBase;
    double[] SalarioRecebido = {2100,2100,2100};
	
	public Vendedor( String nome,int idade,Loja loja,String cidade,String bairro, String rua,double SalarioBase) {
	
		this.nome = nome;
		this.idade = idade;
		this.loja = loja;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.SalarioBase = SalarioBase;
		
	}
	
    public void ApresentarSe() {
    	
    	System.out.println("Nome:" + nome + ", Idade:" + idade + ", Loja: " + loja.nomeFantasia );
    	
    	
    }
    
    public double  CalcularMedia() {
    	
    	double soma = 0;
    	
    	for(double salario : SalarioRecebido) {
    		
    		soma += salario;
    		
    	}
		
    	return soma / SalarioRecebido.length;
    	
    }

    
    public double  CalcularBonus() {
    	
    	return SalarioBase * 0.2;
    	
    }
}
 