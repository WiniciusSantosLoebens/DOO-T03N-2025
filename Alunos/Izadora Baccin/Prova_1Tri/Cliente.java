package prova;

import java.util.Scanner;

public class Cliente {
  private String nome;
  private String cpf;
  
  public  Cliente(String nome, String cpf) { 
	  this.cpf = cpf;
	  this.nome = nome;
  }
  
  public static Cliente cadastrar(Scanner scan) {
	  System.out.println("Nome do cliente: ");
		String nome = scan.next();
		scan.nextLine();
		
		System.out.println("Cpf do cliente: ");
		String cpf = scan.next();
		scan.nextLine();
		
		return new Cliente(nome, cpf);
  }
  
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	this.cpf = cpf;
}
  
}
