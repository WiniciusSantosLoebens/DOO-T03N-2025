import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Serie implements Comparable<Serie> {
    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double notaGeral;
    private String status;
    private LocalDate dataEstreia;
    private LocalDate dataFim;
    private String emissora;


    public Serie(int id, String nome, String idioma, List<String> generos, Double notaGeral,
                 String status, LocalDate dataEstreia, LocalDate dataFim, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.notaGeral = notaGeral;
        this.status = status;
        this.dataEstreia = dataEstreia;
        this.dataFim = dataFim;
        this.emissora = emissora;
    }

    public Serie(String nome, String idioma, List<String> generos, Double notaGeral,
                 String status, LocalDate dataEstreia, LocalDate dataFim, String emissora) {
        this.id = 0;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.notaGeral = notaGeral;
        this.status = status;
        this.dataEstreia = dataEstreia;
        this.dataFim = dataFim;
        this.emissora = emissora;
    }


    // Getters
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getIdioma() {
        return idioma;
    }
    public List<String> getGeneros() {
        return generos;
    }
    public Double getNotaGeral() {
        return notaGeral;
    }
    public String getStatus() {
        return status;
    }
    public LocalDate getDataEstreia() {
        return dataEstreia;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public String getEmissora() {
        return emissora;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }
    public void setNotaGeral(Double notaGeral) {
        this.notaGeral = notaGeral;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setDataEstreia(LocalDate dataEstreia) {
        this.dataEstreia = dataEstreia;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    //permiti que os objetos Serie sejam comparados por ordem alfabetica pelo NOME
    @Override
    public int compareTo(Serie outraSerie) {
        if (this.nome == null && outraSerie.nome == null) return 0;
        if (this.nome == null) return -1; // null vem antes
        if (outraSerie.nome == null) return 1; // null vem depois
        return this.nome.compareToIgnoreCase(outraSerie.nome);
    }

    //para comparar séries com base em seu id
    //usado nos metodos para adicionar/remover das listas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return id == serie.id; // Compara pelo ID
    }

    //se você sobrescreve equals, DEVE sobrescrever hashCode() para garantir que objetos considerados "iguais" por equals()
    @Override
    public int hashCode() {
        return Objects.hash(id); // Gera o hash baseado no ID
    }

    @Override
    public String toString() {
        return "Série: " + nome + "\n" +
                "ID: " + id + "\n" +
                "Idioma: " + idioma + "\n" +
                "Gênero(s): " + (generos != null ? String.join(", ", generos) : "N/A") + "\n" +
                "Nota: " + (notaGeral != null ? notaGeral : "N/A") + "\n" +
                "Status: " + status + "\n" +
                "Data de Estreia: " + (dataEstreia != null ? dataEstreia : "N/A") + "\n" +
                "Data de Término: " + (dataFim != null ? dataFim : "N/A") + "\n" +
                "Emissora: " + (emissora != null ? emissora : "N/A") + "\n";
    }

    //Método para exibir detalhes resumidos de uma serie
    public String toShortString() {
        return String.format("ID: %d | Nome: %s | Nota: %.1f | Status: %s",
                id, nome, (notaGeral != null ? notaGeral : 0.0), status);
    }
}
