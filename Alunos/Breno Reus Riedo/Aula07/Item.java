import java.math.BigDecimal;

public class Item {

    private int id;
    private String nome;
    private String tipo;
    private BigDecimal valor;


    public Item(int id, String nome, String tipo, BigDecimal valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void gerarDescricao(){
        System.out.println("ID:" + id + "\nNome: " + nome + "\nTipo: " + tipo + "\nValor: " + valor);
    }
}
