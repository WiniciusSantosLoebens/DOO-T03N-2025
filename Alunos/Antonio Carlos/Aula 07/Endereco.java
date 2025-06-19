package objetosLoja;

import java.util.Objects;

public class Endereco {
	
	private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String complemento;
    
	public Endereco(String estado, String cidade, String bairro, String rua, String numero, String complemento) {
		super();
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
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

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}
	
	
	public void apresentarLogradouro() {
        String logradouro = rua + ", " + numero;
        if (complemento != null && !complemento.trim().isEmpty()) {
            logradouro += " - " + complemento;
        }
        logradouro += " - " + bairro + ", " + cidade + " - " + estado;
        System.out.println("Endereço: " + logradouro);
    }

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cidade, complemento, estado, numero, rua);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cidade, other.cidade)
				&& Objects.equals(complemento, other.complemento) && Objects.equals(estado, other.estado)
				&& Objects.equals(numero, other.numero) && Objects.equals(rua, other.rua);
	}
	
	
	
    

}
