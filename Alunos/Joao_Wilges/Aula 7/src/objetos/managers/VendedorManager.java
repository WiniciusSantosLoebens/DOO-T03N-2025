package objetos.managers;

import objetos.Vendedor;
import objetos.Loja;
import java.util.ArrayList;

public class VendedorManager {
    private ArrayList<Vendedor> vendedores;

    public VendedorManager() {
        this.vendedores = new ArrayList<>();
    }

    public void cadastrarVendedor(String nome, Loja loja, int idade, String cidade, String rua, String bairro, String complemento, String numero, double salarioBase) {
        Vendedor vendedor = new Vendedor(nome, loja, idade, cidade, rua, bairro, complemento, numero, salarioBase);
        vendedores.add(vendedor);
        System.out.println("Vendedor " + nome + " cadastrado com sucesso.");
    }

    public void excluirVendedor(String nome) {
        Vendedor vendedorParaRemover = encontrarVendedorPorNome(nome);
        if (vendedorParaRemover != null) {
            vendedores.remove(vendedorParaRemover);
            System.out.println("Vendedor " + nome + " foi removido com sucesso.");
        } else {
            System.out.println("Vendedor com nome " + nome + " não encontrado.");
        }
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    private Vendedor encontrarVendedorPorNome(String nome) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNome().equals(nome)) {
                return vendedor;
            }
        }
        return null;
    }
}
