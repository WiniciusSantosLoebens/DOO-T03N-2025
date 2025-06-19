package gerenciador;

import biblioteca.objetos.Cliente;
import biblioteca.objetos.Livro;
import biblioteca.objetos.LivroComum;
import biblioteca.objetos.LivroRaro;
import java.time.temporal.ChronoUnit;


import java.time.LocalDate;


import java.util.List;
import java.util.ArrayList;

public class Biblioteca {
	
	 private String nome;
	    private List<Cliente> clientes;
	    private List<Livro> acervo;

	    // Construtor: Inicializa a biblioteca com nome e listas vazias
	    public Biblioteca(String nome) {
	        this.nome = nome;
	        this.clientes = new ArrayList<>();
	        this.acervo = new ArrayList<>();
	        System.out.println("Biblioteca '" + nome + "' criada.");
	    }

	    // --- Cadastro (Simples e Direto) ---
	    public void cadastrarCliente(Cliente cliente) {
	        if (cliente != null && buscarClientePorId(cliente.getIdCliente()) == null) {
	            this.clientes.add(cliente);
	            System.out.println("OK: Cliente '" + cliente.getNome() + "' cadastrado.");
	        } else {
	            System.out.println("ERRO: Não foi possível cadastrar cliente (nulo ou ID já existe).");
	        }
	    }

	    public void cadastrarLivro(Livro livro) {
	        if (livro != null && buscarLivroPorTitulo(livro.getTitulo()) == null) {
	            this.acervo.add(livro); // Adiciona qualquer tipo de Livro
	            System.out.println("OK: Livro '" + livro.getTitulo() + "' cadastrado.");
	        } else {
	            System.out.println("ERRO: Não foi possível cadastrar livro (nulo ou título já existe).");
	        }
	    }
	    
	    // Busca Cliente por id
	    public Cliente buscarClientePorId(String idCliente) {
	        for (Cliente listarCliente : this.clientes) {
	            if (listarCliente.getIdCliente().equalsIgnoreCase(idCliente)) {
	                return listarCliente;
	            }
	        }
	        return null;
	    }

	    // Busca Livro por Título
	    public Livro buscarLivroPorTitulo(String titulo) {
	        for (Livro listarTitulo : this.acervo) {
	            if (listarTitulo.getTitulo().equalsIgnoreCase(titulo)) {
	                return listarTitulo; // Retorna LivroComum ou LivroRaro
	            }
	        }
	        return null;
	    }
	    
	    public Livro buscarLivroPorAutor(String autor) {
	        for (Livro listarAutor : this.acervo) {
	            if (listarAutor.getAutor().equalsIgnoreCase(autor)) {
	                return listarAutor;
	            }
	        }
	        return null;
	    }
	    
	    public boolean verificarDisponibilidade(String titulo) {
	        Livro livro = buscarLivroPorTitulo(titulo);
	        if (livro == null) {
	            System.out.println("INFO: Livro '" + titulo + "' não encontrado.");
	            return false;
	        }
	        // Se não pode ser emprestado (é Raro), não está disponível para empréstimo
	        if (!livro.podeSerEmprestado()) {
	             System.out.println("INFO: Livro '" + titulo + "' não pode ser emprestado (raro ou regra específica).");
	             return false;
	        }
	        // Se chegou aqui, é um LivroComum. Verifica se está emprestado.
	        // É necessário para acessar o estado específico de LivroComum.
	        LivroComum livroComum = (LivroComum) livro;
	        boolean disponivel = !livroComum.isEmprestado();
	        System.out.println("INFO: Livro '" + titulo + "' está " + (disponivel ? "DISPONÍVEL." : "EMPRESTADO."));
	        return disponivel;
	    }
	    
	    // Metodo para realizar emprestimo caso o livro esteja disponivel e for comum
	    public boolean realizarEmprestimo(String idCliente, String tituloLivro) {
	        Cliente cliente = buscarClientePorId(idCliente);
	        Livro livro = buscarLivroPorTitulo(tituloLivro);

	        // Verifica se cliente e livro existem
	        if (cliente == null || livro == null) {
	            System.out.println("ERRO Empréstimo: Cliente ou Livro não encontrado.");
	            return false;
	        }

	        // Verifica se o livro pode ser emprestado
	        // e se é do tipo LivroComum
	        if (!livro.podeSerEmprestado() || !(livro instanceof LivroComum)) {
	             System.out.println("ERRO Empréstimo: Livro '" + livro.getTitulo() + "' não pode ser emprestado.");
	             return false;
	        }

	        // Se passou, faz o cast e verifica se já está emprestado
	        LivroComum livroComum = (LivroComum) livro;
	        if (livroComum.isEmprestado()) {
	            System.out.println("ERRO Empréstimo: Livro '" + livro.getTitulo() + "' já está emprestado.");
	            return false;
	        }
	        livroComum.registrarEmprestimo(cliente);
	        System.out.println("OK: Empréstimo de '" + livro.getTitulo() + "' para '" + cliente.getNome() + "' realizado.");
	        return true;
	    }
	    
	    // Metodo para realizar uma devolução de livro comum
	    public boolean realizarDevolucao(String tituloLivro) {
	        Livro livro = buscarLivroPorTitulo(tituloLivro);

	        // Verifica se livro existe e se é um LivroComum
	        if (livro == null || !(livro instanceof LivroComum)) {
	            System.out.println("ERRO Devolução: Livro comum '" + tituloLivro + "' não encontrado.");
	            return false;
	        }

	        LivroComum livroComum = (LivroComum) livro;

	        // 2. Verificar se estava realmente emprestado
	        if (!livroComum.isEmprestado()) {
	             System.out.println("ERRO Devolução: Livro '" + livro.getTitulo() + "' não consta como emprestado.");
	             return false;
	        }

	        // Calcular multa usando api ChronoUnit implementando between
	        double multa = 0.0;
	        long diasEmprestado = ChronoUnit.DAYS.between(livroComum.getDataEmprestimo(), LocalDate.now());
	        long diasAtraso = diasEmprestado - 7;
	        if (diasAtraso > 0) {
	            multa = diasAtraso * 3.50; // R$ 3,50 por dia de atraso
	        }

	        // Registro da devolução ao objeto LivroComum
	        Cliente clienteQueDevolveu = livroComum.getClienteEmprestado(); // Pega o nome antes de limpar
	        livroComum.registrarDevolucao();

	        // Informa resultado
	        System.out.println("OK: Devolução de '" + livro.getTitulo() + "' por '" + clienteQueDevolveu.getNome() + "' registrada.");
	        if (multa > 0) {
	            System.out.printf("-> Multa por atraso: R$ %.2f (%d dias de atraso)\n", multa, diasAtraso);
	        } else {
	            System.out.println("-> Devolvido dentro do prazo.");
	        }
	        return true;
	    }
	    
	    // MEtodo para listar Cliente
	    public void listarClientes() {
	        System.out.println("\n--- Clientes Cadastrados ---");
	        if (clientes.isEmpty()) System.out.println("Nenhum cliente.");
	        else clientes.forEach(cliente -> System.out.println("- " + cliente));
	        System.out.println("---------------------------");
	    }

	    // Metodo para listar Acervo
	    public void listarAcervo() {
	        System.out.println("\n--- Acervo da Biblioteca ---");
	        if (acervo.isEmpty()) System.out.println("Acervo vazio.");
	        else acervo.forEach(livro -> System.out.println("- " + livro));
	        System.out.println("---------------------------");
	    }

	    public String getNome() {
	        return nome;
	    }

	    
}
