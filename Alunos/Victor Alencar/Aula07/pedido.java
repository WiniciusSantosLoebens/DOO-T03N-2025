package kdkak;

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

    public pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,
                  cliente cliente, vendedor vendedor, loja loja, ArrayList<item> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(item::getValor).sum();
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido #" + id + " criado em " + dataCriacao);
        System.out.println("Valor Total: R$" + calcularValorTotal());
    }
}
