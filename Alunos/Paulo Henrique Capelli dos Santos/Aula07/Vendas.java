import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
class Vendas {
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal desconto;
    private BigDecimal subtotal;
    private BigDecimal total;
    private LocalDate data;
    private Cliente cliente;
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Vendas(BigDecimal quantidade, BigDecimal valorUnitario, BigDecimal desconto, BigDecimal subtotal, BigDecimal total, Cliente cliente) {
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.desconto = desconto;
        this.subtotal = subtotal;
        this.total = total;
        this.data = LocalDate.now();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Data: " + data.format(FORMATADOR) +
               " | Cliente: " + cliente.getNome() +
               " | CPF: " + cliente.getCpf() +
               " | Quantidade: " + quantidade +
               " | Total: R$" + total;
    }
}