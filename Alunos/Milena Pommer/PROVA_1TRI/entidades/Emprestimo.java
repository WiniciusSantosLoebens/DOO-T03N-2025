package entidades;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

	private Cliente cliente;
	private Livro livro;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucaoReal;
	private double multa;

	public Emprestimo(Cliente cliente, Livro livro, LocalDate dataEmprestimo) {
		super();
		this.cliente = cliente;
		this.livro = livro;
		this.dataEmprestimo = dataEmprestimo;
		this.dataDevolucaoReal = null;
		this.multa = 0.0;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Livro getLivro() {
		return livro;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public LocalDate getDataDevolucaoReal() {
		return dataDevolucaoReal;
	}

	public double getMulta() {
		return multa;
	}

	public void registrarDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucaoReal = dataDevolucao;
		long diasAtraso = ChronoUnit.DAYS.between(dataEmprestimo.plusDays(7), dataDevolucao);
		if (diasAtraso > 0) {
			this.multa = diasAtraso * 3.5;
		} else {
			this.multa = 0;
		}
	}

	public boolean isAtrasado() {
		return dataDevolucaoReal != null && ChronoUnit.DAYS.between(dataEmprestimo.plusDays(7), dataDevolucaoReal) > 0;
	}
}
