package objetos;

import java.util.ArrayList;

public class cliente {

    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private ArrayList<String> vendas;  // Lista para armazenar as vendas feitas pelo cliente

    // Construtor
    public cliente(String nome, String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.vendas = new ArrayList<>();  // Inicializa a lista de vendas
    }

    // Métodos para acessar e modificar os atributos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Adicionar uma venda à lista de vendas do cliente
    public void adicionarVenda(String venda) {
        vendas.add(venda);
    }

    // Exibir as vendas realizadas pelo cliente
    public void exibirVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            System.out.println("Vendas realizadas por " + nome + ":");
            for (String venda : vendas) {
                System.out.println(venda);
            }
        }
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CPF: " + cpf + ", Telefone: " + telefone + ", Endereço: " + endereco;
    }
}
