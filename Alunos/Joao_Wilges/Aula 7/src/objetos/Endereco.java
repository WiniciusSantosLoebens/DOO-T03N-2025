package objetos;

public class Endereco {
    private String cidade;
    private String bairro;
    private String rua;
    private String complemento;
    private String numero;

    public Endereco(String cidade, String bairro, String rua, String complemento, String numero) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.complemento = complemento;
        this.numero = numero;
    }
}
