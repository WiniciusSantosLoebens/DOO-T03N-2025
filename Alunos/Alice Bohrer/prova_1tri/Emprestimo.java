package sistemaBiblioteca;
import objetos.Livro;
import objetos.Cliente;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
	
	Livro livro;
	Cliente cliente;
	LocalDate dataInicio;
	LocalDate dataFim;
	
	
	public Emprestimo(Livro livro, Cliente cliente) {
		
		this.cliente = cliente;
		this.livro = livro;
		this.dataInicio = LocalDate.now();
		
	}
	
	public void devolver () {
		
	    this.dataFim = LocalDate.now();
	    
        long dias = ChronoUnit.DAYS.between(dataInicio, dataFim);
        
        if (dias > 7) {
        	
            double multa = (dias - 7) * 3.5;
            
            System.out.println("Multa por atraso: R$ " + multa);
            
        } else {
        	
            System.out.println("Livro devolvido sem multa.");
        }
		
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
