package biblioteca.objetos;

import java.time.LocalDate;

// subClasse livroComum que herda da Superclasse Livro
public class LivroComum extends Livro {
	
	private Cliente clienteEmprestado;
    private LocalDate dataEmprestimo;

    
    public LivroComum(String titulo, String autor) {
        super(titulo, autor); 
        this.clienteEmprestado = null; 
        this.dataEmprestimo = null;   
    }
    
    // Polimorfismo representa o metodo abstrato firmado na classe Pai Livro
    // Neste caso indica que livros comuns podem ser emprestados
    @Override
    public boolean podeSerEmprestado() {
        return true; 
    }
    
    public boolean isEmprestado() {
        return this.clienteEmprestado != null;
    }

    
    public Cliente getClienteEmprestado() {
        return clienteEmprestado;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public void registrarEmprestimo(Cliente cliente) {
        if (!isEmprestado()) { 
            this.clienteEmprestado = cliente;
            this.dataEmprestimo = LocalDate.now(); 
        }
    }
    
    public void registrarDevolucao() {
        this.clienteEmprestado = null;
        this.dataEmprestimo = null;
    }

    // Método toString que complementa o toString de Livro retornando strings que siginifica que este livro pode ser emprestado
    @Override
    public String toString() {
        String status = isEmprestado() ? "Emprestado para: " + clienteEmprestado.getNome() + " em " + dataEmprestimo
                                       : "Disponível";
        return super.toString() + ", Tipo=Comum, Status=" + status + "]";
    }
	


	
	
}
