package objetos;

import java.util.ArrayList;

public class loja {
    private String nome;
    private String localizacao;
    private ArrayList<vendedor> vendedores;

    // Construtor
    public loja(String nome, String localizacao) {
        this.nome = nome;
        this.localizacao = localizacao;
        this.vendedores = new ArrayList<>();
    }

    // Métodos para acessar e modificar os atributos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void adicionarVendedor(vendedor v) {
        this.vendedores.add(v);
    }

    public void exibirVendedores() {
        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
        } else {
            for (vendedor v : vendedores) {
                System.out.println(v);
            }
        }
    }
}
