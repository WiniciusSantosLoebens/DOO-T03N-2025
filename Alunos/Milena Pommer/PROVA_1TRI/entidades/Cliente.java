package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
	
	public static Scanner sc = new Scanner(System.in);
	
	private Integer idCliente;
	private String nomeCliente;
	private List<Emprestimo> emprestimosAtivos;
	
	public Cliente(Integer idCliente, String nomeCliente, List<Emprestimo> emprestimosAtivos) {
		super();
		this.idCliente = idCliente;
		this.nomeCliente = nomeCliente;
		this.emprestimosAtivos = new ArrayList<>();
	}
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public List<Emprestimo> getEmprestimosAtivos() {
		return emprestimosAtivos;
	}

	public void adicionarEmprestimo(Emprestimo emprestimo) {
		emprestimosAtivos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimosAtivos.remove(emprestimo);
    }
}
