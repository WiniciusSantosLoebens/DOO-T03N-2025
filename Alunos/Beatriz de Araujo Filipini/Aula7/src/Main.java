import model.*;

import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
    Cliente cliente = new Cliente("Carlos", 30);
    Vendedor vendedor = new Vendedor("Fernanda", 28);
    ArrayList<Item> itens = new ArrayList<>();

    itens.add(new Item(1, "Vaso de planta", "Decoração", 120.0));
    itens.add(new Item(2, "Terra adubada", "Jardinagem", 40.0));

    Date agora = new Date();
    Date vencimento = new Date(agora.getTime() + 3 * 24 * 60 * 60 * 1000); // +3 dias

    ProcessaPedido processador = new ProcessaPedido();
    Pedido pedido = processador.processar(101, agora, agora, vencimento, cliente, vendedor, "My Plant", itens);

        if (pedido != null) {
            pedido.gerarDescricaoVenda();
        }
    }
}
