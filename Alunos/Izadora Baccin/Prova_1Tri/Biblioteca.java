package prova;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    List<LivroRaro> livrosRaros = new ArrayList<>();
    List<LivroComun> livrosComuns = new ArrayList<>();
    List<Cliente> clientes;

    public Biblioteca() {
        livrosComuns = new ArrayList<>();
        livrosRaros = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente Cadastrado! ");
    }

    public void cadastrarLivroComun(LivroComun livro) {
        livrosComuns.add(livro);
        System.out.println("Livro Comum Cadastrado!");
    }

    public void cadastrarLivroRaro(LivroRaro livro) {
        livrosRaros.add(livro);
        System.out.println("Livro Raro Cadastrado!");
    }

    public void buscarLivroTitulo(String tituloBuscado) {
        boolean encontrado = false;
        String tituloNormalizado = normalizarTexto(tituloBuscado);

        for (LivroComun livro : livrosComuns) {
            if (normalizarTexto(livro.getTitulo()).equals(tituloNormalizado)) {
                System.out.println("Livro Comum: " + livro.getTitulo() + " - " + livro.getAutora());
                encontrado = true;
            }
        }

        for (LivroRaro livro : livrosRaros) {
            if (normalizarTexto(livro.getTitulo()).equals(tituloNormalizado)) {
                System.out.println("Livro Raro: " + livro.getTitulo() + " - " + livro.getAutora() +
                                   "\nDescrição: " + livro.getExplicacao());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Livro não encontrado!");
        }
    }

    public void buscarLivroAutora(String autoraBuscada) {
        boolean encontrado = false;
        String autoraNormalizada = normalizarTexto(autoraBuscada);

        for (LivroComun livro : livrosComuns) {
            if (normalizarTexto(livro.getAutora()).equals(autoraNormalizada)) {
                System.out.println("Livro Comum Encontrado: " + livro.getTitulo() +
                                   "\nAutor/Autora: " + livro.getAutora());
                encontrado = true;
            }
        }

        for (LivroRaro livro : livrosRaros) {
            if (normalizarTexto(livro.getAutora()).equals(autoraNormalizada)) {
                System.out.println("Livro Raro Encontrado: " + livro.getTitulo() +
                                   "\nAutor/Autora: " + livro.getAutora() +
                                   "\nExplicação: " + livro.getExplicacao());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Autor/Autora: " + autoraBuscada + " não encontrada!");
        }
    }

    public double calcularMulta(float diasAtraso) {
        if (diasAtraso > 7) {
            float diasMulta = diasAtraso - 7;
            return diasMulta * 3.50;
        } else {
            return 0.0;
        }
    }

    private String normalizarTexto(String texto) {
        if (texto == null) return "";
        String textoSemAcento = Normalizer.normalize(texto.trim().toLowerCase(), Normalizer.Form.NFD);
        return textoSemAcento.replaceAll("\\p{M}", "");
    }
}