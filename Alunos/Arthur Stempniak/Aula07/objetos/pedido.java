package objetos;

import java.util.ArrayList;
import java.util.Date;

public class pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private cliente cliente;
    private vendedor vendedor;
    private loja loja;
    private ArrayList<item> itens;

    public pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, cliente cliente, vendedor vendedor, loja loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (item item : itens) {
            total += item.getValor();  // Usando o método getValor()
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Data de Criação: " + dataCriacao);
        System.out.println("Valor Total: R$ " + calcularValorTotal());
    }
}
