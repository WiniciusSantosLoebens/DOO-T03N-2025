import java.util.ArrayList;
import java.util.List;

public class Cliente {
    public String nome;
    public static List<Cliente> clientes = new ArrayList<Cliente>();

    public Cliente(String nome){
        this.nome = nome;
    }
}
