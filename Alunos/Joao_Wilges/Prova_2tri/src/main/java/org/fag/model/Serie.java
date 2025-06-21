package org.fag.model;

import java.time.LocalDate;
import java.util.List;

public class Serie {
    private String id; //show:id
    private String nome; //show:name
    private String idioma; //show:language
    private List<String> generos; //show:genres
    private Double nota_geral; //show:rating:average
    private String estado; //show:status
    private LocalDate data_estreia; //show:premiered
    private LocalDate data_termino; //show:ended
    private String nome_emissora; //show:network:name

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public Double getNotaGeral() {
        return nota_geral;
    }

    public String getEstado() {
        return estado;
    }

    public LocalDate getDataEstreia() {
        return data_estreia;
    }

    public Serie(String id, String nome, String idioma, List<String> generos, Double nota_geral, String estado, LocalDate data_estreia, LocalDate data_termino, String nome_emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.nota_geral = Double.valueOf(nota_geral);
        this.estado = estado;
        this.data_estreia = data_estreia;
        this.data_termino = data_termino;
        this.nome_emissora = nome_emissora;
    }

    @Override
    public String toString() {
        return String.format(
                "Serie { Nome='%s'; Idioma='%s'; Generos='%s'; Nota Geral='%s'; Estado='%s'; Data Estreia='%s'; Data Termino='%s'; Emissora='%s' }",
                nome, idioma, generos, nota_geral, estado, data_estreia, data_termino, nome_emissora
        );
    }
}