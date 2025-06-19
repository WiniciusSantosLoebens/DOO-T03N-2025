package objetos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class Pedido {
	private static final AtomicLong idGenerator = new AtomicLong(0);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private long id;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private List<Item> itens;
    private double valorTotal;
    private boolean descontoAplicado;

    public Pedido(Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens, double valorTotal, boolean descontoAplicado) {
        this.id = idGenerator.incrementAndGet();
        this.dataCriacao = LocalDate.now();
        this.dataVencimentoReserva = this.dataCriacao.plusDays(3);
        this.dataPagamento = null; 
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = new ArrayList<>(itens); 
        this.valorTotal = valorTotal;
        this.descontoAplicado = descontoAplicado;
    }

    public double calcularValorTotal() {
    
        return this.valorTotal;
    }

    public void gerarDescricaoVenda() {
        System.out.println("--- Pedido ID: " + this.id + " ---");
        System.out.println("Data Criação: " + this.dataCriacao.format(DATE_FORMATTER));
        System.out.println("Cliente: " + (this.cliente != null ? this.cliente.getNome() : "N/A"));
        System.out.println("Vendedor: " + (this.vendedor != null ? this.vendedor.getNome() : "N/A"));
        System.out.println("Loja: " + (this.loja != null ? this.loja.getNomeFantasia() : "N/A"));
        System.out.println("Itens:");
        if (this.itens.isEmpty()) {
            System.out.println("  (Nenhum item)");
        } else {
            for (Item item : this.itens) {
                System.out.printf("  - %s (R$ %.2f)\n", item.getNome(), item.getValor());
            }
        }
        System.out.printf("Valor Total: R$ %.2f %s\n", this.valorTotal, (this.descontoAplicado ? "(Desconto 5% aplicado)" : ""));
        System.out.println("Vencimento Reserva: " + this.dataVencimentoReserva.format(DATE_FORMATTER));
        System.out.println("Status Pagamento: " + (isPago() ? "Pago em " + this.dataPagamento.format(DATE_FORMATTER) : "Pendente"));
        System.out.println("--------------------");
    }

    // Getters
    public long getId() { return id; }
    public LocalDate getDataCriacao() { return dataCriacao; }
    public LocalDate getDataPagamento() { return dataPagamento; }
    public LocalDate getDataVencimentoReserva() { return dataVencimentoReserva; }
    public Cliente getCliente() { return cliente; }
    public Vendedor getVendedor() { return vendedor; }
    public Loja getLoja() { return loja; }
    public List<Item> getItens() { return new ArrayList<>(itens); } // Retorna cópia
    public double getValorTotal() { return valorTotal; }
    public boolean isDescontoAplicado() { return descontoAplicado; }
    public boolean isPago() { return dataPagamento != null; }

    // Setter para pagamento
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}