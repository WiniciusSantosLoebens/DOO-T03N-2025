import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Emprestimo {
    private Livro livro;
    private Cliente cliente;
    private LocalDate dataEmprestimo;

    public Emprestimo(Livro livro, Cliente cliente){
        this.livro = livro;
        this.cliente = cliente;
        this.dataEmprestimo = LocalDate.now();
    }
    public Livro getLivro(){return livro;}

    public double calcularMulta(){
        long dias = ChronoUnit.DAYS.between(dataEmprestimo, LocalDate.now());
        if (dias > 7) {
            return (dias - 7) * 3.5;
        }
        return 0.0;
        }
    }

