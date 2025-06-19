package sistemaBiblioteca;
import objetos.Livro;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import objetos.Cliente;

public class Biblioteca {
	
	public static List <Livro> livros = new ArrayList <>();
    public 	static List <Cliente> clientes = new ArrayList <>(); 
	public static List <Emprestimo> emprestimos = new ArrayList <>();
	
	public void CadastrarCliente(Cliente c) {
		
		clientes.add(c);
		
	}
	
	public static void CadastarLivro(Livro l) {
		
		
         livros.add(l);
		
	}
	
	public static Optional <Livro> BuscarPorTitulo (String nome) {
		
			for( Livro t1 : livros) {
				
				if(t1.getNome().equalsIgnoreCase(nome)) {
					
					return Optional.of(t1);
				
				}
				
			}
			
			return Optional.empty();
			
		
	}
		
	public static Optional <Livro> BuscarPorAutor (String autor) {
		
		for( Livro a1 : livros) {
			
			if(a1.getAutor().equalsIgnoreCase(autor)) {
				
				return Optional.of(a1);
			
			}
			
		}
		
		return Optional.empty();
	
	}
	
	public void VerDisponibilidade(Livro livro) {
		
		 for (Livro l : livros) {
	        	
	            if (!l.isDisponibilidade()) {
	            
	              System.out.println(" Livro " + l.getNome() + " não está disponível.");


	}else {
		
		System.out.println("O livro "+ livro.getNome() + "esta disponivel para emprestimo");
	}
	
		
	           
		
		
	}


	}
	
	public static void RealizarEmprestimo(Livro livro, Cliente cliente) {
		
		 if (!livros.contains(livro)) {
			 
			 System.out.println("Livro '" + livro.getNome() + "' não encontrado no acervo.");
             return;
			 
		 }
            
         
             if (livro.isDisponibilidade()) {

                 Emprestimo novoEmprestimo = new Emprestimo(livro, cliente); 
                 emprestimos.add(novoEmprestimo);


                 System.out.println("Livro '" + livro.getNome() + "' emprestado para '" + cliente.getNome() + "'.");

             } else {
              
                 System.out.println(" Livro '" + livro.getNome() + "' já está emprestado e não pode ser emprestado novamente.");
		
		
	            
	              
		
		
		
		
		
		
		
	            
		 }
		
		
	}
	
	public static void realizarDevolucao(Livro livro) {
	    for (Emprestimo emp : emprestimos) {
	        if (emp.getLivro().equals(livro)) {
	            emp.devolver();
	            livro.setDisponibilidade(true); 
	            emprestimos.remove(emp); 
	            System.out.println("Devolução registrada com sucesso.");
	            return;
	        }
	    }
	    System.out.println("Esse livro não está emprestado ou não existe.");
	}


		
	}	
		
		
	


