package classes;

public class Loja {
	
	
     public String nomeFantasia;
	 String razaoSocial;
	 String cnpj;
	 String cidade;
	 String bairro;
     String rua;
     public Vendedor[] vendedores;
     public Cliente[] clientes;
	
	
    
    
	public Loja(String nomeFantasia, String razaoSocial, String cnpj,String cidade, String bairro, String rua,
                Vendedor[] vendedores, Cliente[] clientes) {
		
		
		
    this.nomeFantasia = nomeFantasia;
	this.razaoSocial = razaoSocial;
	this.cnpj = cnpj;
    this.cidade = cidade;
    this.bairro = bairro;
    this.rua = rua;
	this.vendedores = vendedores;
	this.clientes = clientes;
			
	}

    public void contarClientes() {
    	
	        System.out.println("Total de clientes: " + clientes.length);
    }

	public void contarVendedores() {
		
	        System.out.println("Total de vendedores: " + vendedores.length);
	        
   }

    public void apresentarse() {
    	
	        System.out.println("Loja: " + nomeFantasia);
	        System.out.println("CNPJ: " + cnpj);
	        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
   }


	
	
	
	
	
	
	
	
	

}
