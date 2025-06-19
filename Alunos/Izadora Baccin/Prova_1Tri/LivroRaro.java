package prova;

import java.util.Scanner;

public class LivroRaro {
 private String titulo;
 private String autora;
 private String explicacao;
 
 public LivroRaro(String titulo, String autora, String explicacao) {
  this.titulo = titulo;
  this.autora = autora;
  this.explicacao = explicacao;
 }
 
 public static LivroRaro cadastrar(Scanner scan) {
	 System.out.print("Título do livro raro: ");
     String titulo = scan.next();
     scan.nextLine();
     System.out.print("Autor(a): ");
     String autora = scan.next();
     scan.nextLine();
     System.out.print("Por que esse livro é raro? ");
     String explicacao = scan.nextLine();
     
     return new LivroRaro(titulo, autora, explicacao);
 }

public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getAutora() {
	return autora;
}
public void setAutora(String autora) {
	this.autora = autora;
}
public String getExplicacao() {
	return explicacao;
}
public void setExplicacao(String explicacao) {
	this.explicacao = explicacao;
}
}
