package objetos;

import java.util.ArrayList;

public class cliente extends pessoa {
    private String cpf;
    private String telefone;
    private ArrayList<String> vendas;

    public cliente(String nome, endereco endereco, String cpf, String telefone) {
        super(nome, endereco);
        this.cpf = cpf;
        this.telefone = telefone;
        this.vendas = new ArrayList<>();
    }

    public void adicionarVenda(String venda) {
        vendas.add(venda);
    }

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

    public String toString() {
        return "Cliente: " + nome + ", CPF: " + cpf + ", Telefone: " + telefone;
    }
}
