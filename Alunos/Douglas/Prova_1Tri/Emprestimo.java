import java.time.LocalDate;

public class Emprestimo {
    int id;
    Cliente cliente;
    Livro livro;
    LocalDate dataSaida;
    LocalDate dataDevolucao;
    float multa;

    public Emprestimo(int id, Cliente cliente, Livro livro, LocalDate dataSaida) {
        this.id = id;
        this.cliente = cliente;
        this.livro = livro;
        this.dataSaida = dataSaida;
        this.dataDevolucao = null;
        this.multa = 0;
    }

    public float contagemDias() {
        float multa = 0;

        long dias = java.time.temporal.ChronoUnit.DAYS.between(dataSaida, dataDevolucao);
        if (dias > 7) {
            multa = (float) (3.50 * (dias - 7));
        }

        return multa;
    }

    @Override
    public String toString() {
        return 
            "ID: " + id +
            "\nCliente: " + cliente.nome +
            "\nLivro: " + livro.titulo + " - Autor: " + livro.autor +
            "\nData de Saída: " + dataSaida +
            "\nData de Devolução: " + dataDevolucao +
            "\nMulta: " + multa;
    }


}
