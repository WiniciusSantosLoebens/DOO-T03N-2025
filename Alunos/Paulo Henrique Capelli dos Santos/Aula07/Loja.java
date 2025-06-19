import java.util.*;
;
public class Loja {;
 private String nomeFantasia;
 private String razaoSocial;
 private String cnpj;
 private Endereco endereco;
 private List<Vendedor> vendedores = new ArrayList<>();
 private List<Cliente> clientes = new ArrayList<>();
 private List<Gerente> gerentes = new ArrayList<>();
;
 public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {;
 this.nomeFantasia = nomeFantasia;
 this.razaoSocial = razaoSocial;
 this.cnpj = cnpj;
 this.endereco = endereco;
 };
;
 public Loja(String string, String string2, String string3, String string4, String string5, String string6) {
    //TODO Auto-generated constructor stub
}
 public void adicionaVendedor(Vendedor vendedor) {
 vendedores.add(vendedor);
 };
;
 public void adicionaCliente(Cliente cliente) {
 clientes.add(cliente);
 };
;
 public void adicionaGerente(Gerente gerente) {
 gerentes.add(gerente);
 };
;
 public void apresentarse() {;
 System.out.println("Loja: " + nomeFantasia + " | CNPJ: " + cnpj);
 endereco.apresentarLogradouro();
 };
;
 public void listarClientes() {
 for (Cliente c : clientes) c.apresentarse();
 };
;
 public void listarVendedores() {
 for (Vendedor v : vendedores) v.apresentarse();
 };
;
 public Cliente buscarClientePorCpf(String cpf) {
 for (Cliente c : clientes) {
 if (c.getCpf().equals(cpf)) return c;
 };
 return null;
 };
;
 public String getNomeFantasia() { return nomeFantasia; }
}