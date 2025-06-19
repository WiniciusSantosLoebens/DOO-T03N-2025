
class Endereco {
    private String estado, cidade, bairro, numero, complemento;

    public Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println("Endereço: " + ruaCompleta());
    }

    public String ruaCompleta() {
        return bairro + ", " + numero + (complemento.isEmpty() ? "" : ", " + complemento) + " - " + cidade + ", " + estado;
    }

    public String getCidade() { return cidade; }
    public String getBairro() { return bairro; }
    public String getRua() { return numero; }
}
