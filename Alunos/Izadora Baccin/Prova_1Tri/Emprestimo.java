package prova;

import java.time.Duration;
import java.time.LocalDate;

public class Emprestimo {
	private Cliente cliente;
	private LivroComun livro;
	private LocalDate dataEmprestimo;
	private LocalDate dataDevolucao;
	
	public void emprestimo(Cliente cliente, LivroComun livro) {
		this.cliente = cliente;
		this.livro = livro;
		this.dataEmprestimo = LocalDate.now();
		this.dataDevolucao = null;
	}
	
	public void devolver() {
		this.dataDevolucao = LocalDate.now();
		livro.setDisp(true);
	}
	
	public double calcularMulta() {
		if(dataDevolucao == null) return 0.0;
		float dias = Duration.between(dataEmprestimo.atStartOfDay(), dataDevolucao.atStartOfDay()).toDays();
		if(dias>7) {
			return (dias - 7) * 3.50;
		}
			return 0.0; 
	}
	
	public String toString() {
		return cliente+ "-"+livro+"\n Emprestado em: "+dataEmprestimo+
				(dataDevolucao != null ? "Devolvido em: "+dataDevolucao+"\n Multa: R$"+calcularMulta(): "Em andamento");
	} 
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public LivroComun getLivro() {
		return livro;
	}
	public void setLivro(LivroComun livro) {
		this.livro = livro;
	}
	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

}
