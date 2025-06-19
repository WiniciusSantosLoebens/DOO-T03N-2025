package objetosMyPlant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;

public class Pedido {

	private String id;
	private Date dataCriacao;
	private Date dataPagamento;
	private Date dataVencimentoReserva;
	private Cliente cliente;
	private Vendedor vendedor;
	private Loja loja;
	private List<Item> itens;

	public Pedido(Cliente cliente, Vendedor vendedor, Loja loja) {
		this.id = UUID.randomUUID().toString().substring(0, 8);
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.loja = loja;
		this.itens = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimentoReserva() {
		return dataVencimentoReserva;
	}

	public void setDataVencimentoReserva(Date dataVencimentoReserva) {
		this.dataVencimentoReserva = dataVencimentoReserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Item> getItens() {
		return new ArrayList<>(itens);
	}

	public void adicionarItem(Item item) {
		if (item != null) {
			this.itens.add(item);
		}
	}

	public double calcularValorTotal() {
		return itens.stream().mapToDouble(Item::getValor).sum();
	}

	public void gerarDescricaoVenda() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dataStr = (dataCriacao != null) ? sdf.format(dataCriacao) : "N/A";
		System.out.printf("Pedido ID: %s | Criado em: %s | Cliente: %s | Valor Total: R$%.2f\n", id, dataStr,
				cliente.getNome(), calcularValorTotal());
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		String dataCriacaoStr = (dataCriacao != null) ? sdf.format(dataCriacao) : "N/A";
		String statusPag = (dataPagamento != null) ? "Pago em " + sdf.format(dataPagamento) : "Pendente";
		return String.format(
				"Pedido[ID=%s, Data=%s, Cliente=%s, Vendedor=%s, Loja=%s, Status=%s, Total=R$%.2f, Itens=%d]", id,
				dataCriacaoStr, cliente.getNome(), vendedor.getNome(), loja.getNomeFantasia(), statusPag,
				calcularValorTotal(), itens.size());
	}
}
