package objetos;

public class Endereco {

	private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String complemento;

    public Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        String comp = (complemento != null && !complemento.isEmpty()) ? " - " + complemento : "";
        System.out.printf("Endereço: %s, %s%s - %s/%s\n",
                bairro, numero, comp, cidade, estado);
    }

    @Override
    public String toString() {
         String comp = (complemento != null && !complemento.isEmpty()) ? " - " + complemento : "";
        return String.format("%s, %s%s - %s/%s", bairro, numero, comp, cidade, estado);
    }

    public String getEstado() {
    	return estado; 
    	}
    public String getCidade() { 
    	return cidade; 
    	}
    public String getBairro() {
    	return bairro; 
    	}
    public String getNumero() {
    	return numero;
    	}
    public String getComplemento() {
    	return complemento;
    	}

}