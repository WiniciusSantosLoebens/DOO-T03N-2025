package objetos;

public class endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String complemento;

    // Construtor
    public endereco(String estado, String cidade, String bairro, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    // Método para apresentar o logradouro de forma interessante
    public void apresentarLogradouro() {
        System.out.println("Endereço: " + numero + ", " + bairro + " - " + cidade + ", " + estado + " - " + complemento);
    }
}
