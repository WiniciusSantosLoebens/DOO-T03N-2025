package kdkak;

import java.util.ArrayList;

public class loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private endereco endereco;
    public ArrayList<vendedor> vendedores;
    public ArrayList<cliente> clientes;

    public loja(String nomeFantasia, String razaoSocial, String cnpj, endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void adicionarVendedor(vendedor v) {
        vendedores.add(v);
    }

    public void adicionarCliente(cliente c) {
        clientes.add(c);
    }

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.print("Endereço: ");
        endereco.apresentarLogradouro();
    }
}
