package prova;

import java.util.Scanner;

public class LivroComun {
 private String titulo;
 private String autora;
 private boolean disp; 
 
 public LivroComun(String titulo, String autora) {
	 this.autora = autora;
	 this.titulo = titulo;
	 this.disp = true;
 }
 
 public static LivroComun cadastrar(Scanner scan) {
     System.out.print("Título do livro comum: ");
     String titulo = scan.nextLine();
     System.out.print("Autor(a): ");
     String autora = scan.nextLine();
     
     return new LivroComun(titulo, autora);
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
public boolean isDisp() {
	return disp;
}
public void setDisp(boolean disp) {
	this.disp = disp;
}

}
