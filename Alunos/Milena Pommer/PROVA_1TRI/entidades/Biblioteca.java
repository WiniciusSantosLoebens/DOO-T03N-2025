package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

	private List<Livro> livros;
	private List<Cliente> clientes;

	public Biblioteca() {
		this.livros = new ArrayList<>();
		this.clientes = new ArrayList<>();
	}

	public void cadastrarCliente(Cliente cliente) {
		this.clientes.add(cliente);
	}
	
	public Cliente buscarClientePorId(int id) {
		for (Cliente c : clientes) {
			if (c.getIdCliente() == id) {
				return c;
			}
		}
		return null;
	}

	public void adicionarLivro(Livro livro) {
		this.livros.add(livro);
	}

	public Livro buscarLivroTitulo(String titulo) {
		for (Livro livro : livros) {
			if (livro.getTituloLivro().equalsIgnoreCase(titulo)) {
				return livro;
			}
		}
		return null;
	}

	public Livro buscarLivroAutor(String autor) {
		for (Livro livro : livros) {
			if (livro.getAutorLivro().equalsIgnoreCase(autor)) {
				return livro;
			}
		}
		return null;
	}

	public void realizarEmprestimo(Cliente cliente, Livro livro) {
		if (verificarDisponibilidade(livro)) {
			Emprestimo emprestimo = new Emprestimo(cliente, livro, LocalDate.now());
			cliente.adicionarEmprestimo(emprestimo);
			livro.setDisponibilidade(false);
			System.out.println("Empréstimo realizado com sucesso!");
		} else {
			System.out.println("Livro não disponível para empréstimo.");
		}
	}

	public void realizarDevolucao(Cliente cliente, Livro livro, java.time.LocalDate dataDevolucao) {
		for (Emprestimo emprestimo : cliente.getEmprestimosAtivos()) {
			if (emprestimo.getLivro().equals(livro)) {
				emprestimo.registrarDevolucao(dataDevolucao);
				livro.setDisponibilidade(true);
				cliente.removerEmprestimo(emprestimo);
				System.out.println("Devolução realizada com sucesso. Multa: R$ " + emprestimo.getMulta());
				return;
			}
		}
		System.out.println("Empréstimo não encontrado.");
	}

	public boolean verificarDisponibilidade(Livro livro) {
		return livro.isDisponibilidade();
	}
}
