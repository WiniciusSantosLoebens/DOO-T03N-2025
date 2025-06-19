package objetos.managers;

import objetos.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoManager {
    private List<Pedido> pedidos;

    public PedidoManager() {
        this.pedidos = new ArrayList<>();
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public boolean removerPedido(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                pedidos.remove(pedido);
                return true;
            }
        }
        return false;
    }

    public int contarPedidos() {
        return pedidos.size();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
