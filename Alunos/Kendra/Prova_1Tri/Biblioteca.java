package objeto;
import java.util.*;
import java.time.*;

public class Biblioteca {
	
	    private ArrayList<Cliente> clientes = new ArrayList<>();
	    private ArrayList<Livro> livros = new ArrayList<>();
	    private HashMap<Livro, LocalDate> registrosEmprestimos = new HashMap<>();

	    public void cadastrarCliente(Cliente c) {
	        clientes.add(c);
	    }
	    
	    public Cliente buscarClientePorCpf(String cpf) {
	        for (Cliente c : clientes) {
	            if (c.getCpf().equals(cpf)) {
	                return c;
	            }
	        }
	        return null;
	    }

	    public void cadastrarLivro(Livro l) {
	        livros.add(l);
	    }

	    public Livro buscarPorTitulo(String titulo) {
	        for (Livro l : livros) {
	            if (l.getTitulo().equalsIgnoreCase(titulo)) {
	                return l;
	            }
	        }
	        return null;
	    }

	    public Livro buscarPorAutor(String autor) {
	        for (Livro l : livros) {
	            if (l.getAutor().equalsIgnoreCase(autor)) {
	                return l;
	            }
	        }
	        return null;
	    }

	   
	    public void emprestarLivro(Livro livro, Cliente cliente) {
	        if (livro instanceof LivroRaro) {
	            System.out.println("Este livro é raro e não pode ser emprestado.\n");
	            return; 
	        }

	        if (!livro.isEmprestado()) {
	            livro.emprestar();
	            livro.setClienteEmprestimo(cliente); 
	            registrosEmprestimos.put(livro, LocalDate.now());
	            System.out.println("Livro emprestado com sucesso para " + cliente.getNome());
	        } else {
	            System.out.println("Livro já está emprestado.");
	        }
	    }

	    public void devolverLivro(Livro livro) {
	        if (livro.isEmprestado()) {
	            LocalDate dataEmprestimo = registrosEmprestimos.get(livro);
	            LocalDate hoje = LocalDate.now();
	            long diasAtraso = Duration.between(dataEmprestimo.atStartOfDay(), hoje.atStartOfDay()).toDays() - 7;

	            if (diasAtraso > 0) {
	                double multa = diasAtraso * 3.5;
	                System.out.printf("Livro devolvido com atraso. Multa: R$ %.2f\n", multa);
	            } else {
	                System.out.println("Livro devolvido sem atraso.");
	            }

	            Cliente cliente = livro.getClienteEmprestimo(); 
	            System.out.println("Livro devolvido por: " + (cliente != null ? cliente.getNome() : "Desconhecido"));

	            livro.devolver();
	            livro.setClienteEmprestimo(null); 
	            registrosEmprestimos.remove(livro);
	        } else {
	            System.out.println("Este livro não está emprestado.");
	        }
	    }

	    public boolean estaDisponivel(String titulo) {
	        Livro l = buscarPorTitulo(titulo);
	        return l != null && !l.isEmprestado();
	    }
	}
	
	