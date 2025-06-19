package kdkak;

public class endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private int numero;
    private String complemento;

    public endereco(String estado, String cidade, String bairro, int numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println(bairro + ", " + cidade + " - " + estado + ", Nº: " + numero + ", " + complemento);
    }
}
