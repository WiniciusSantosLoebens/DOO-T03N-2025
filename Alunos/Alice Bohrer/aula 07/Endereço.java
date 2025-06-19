package classes;

public class Endereço {
	
	String cidade;
	String estado;
	String bairro;
	String numero;
    String complemento;
    
    
	public Endereço(String cidade,String estado,String bairro,String numero,String complemento) {
		
		this.cidade = cidade;
		this.estado = estado;
		this.bairro = bairro;
		this.numero = numero;
	    this.complemento = complemento;
		
		
	}
    
	public void ApresentarLogradouro() {
		
		System.out.println("Endereço: " +Endereco());
		
	}
    
	public String Endereco() {
		 
		
    return bairro + ", " + numero + " - " + complemento + ", " + cidade + " - " + estado;	
		
	}
	
	

}
