import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class GerenciadorSeries {
    private String nomeUsuario;
    private List<Serie> favoritas;
    private List<Serie> assistidas;
    private List<Serie> desejaAssistir;

    public GerenciadorSeries() {
        this.favoritas = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejaAssistir = new ArrayList<>();
        this.nomeUsuario = "Usuário Padrão";
    }

    //metodos do usuario
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        if (nomeUsuario != null && !nomeUsuario.trim().isEmpty()) {
            this.nomeUsuario = nomeUsuario.trim();
            System.out.println("Nome de usuário atualizado para: " + this.nomeUsuario);
        } else {
            System.out.println("Erro: Nome de usuário não pode ser vazio.");
        }
    }

    //metodos para adicionar series a listas
    public boolean adicionarSerieAssistida(Serie serie) {
        if (serie == null) {
            System.out.println("Erro: Não é possível adicionar uma série nula.");
            return false;
        }
        if (!assistidas.contains(serie)) {
            assistidas.add(serie);
            System.out.println("Série '" + serie.getNome() + "' adicionada à lista de assistidas.");
            return true;
        } else {
            System.out.println("Série '" + serie.getNome() + "' já está na lista de assistidas.");
            return false;
        }
    }

    //Adiciona uma serie a lista de favoritos
    public boolean adicionarSerieFavorita(Serie serie) {
        if (serie == null) {
            System.out.println("Erro: Não é possível adicionar uma série nula.");
            return false;
        }
        if (!favoritas.contains(serie)) {
            favoritas.add(serie);
            System.out.println("Série '" + serie.getNome() + "' adicionada à lista de favoritos.");
            return true;
        } else {
            System.out.println("Série '" + serie.getNome() + "' já está na lista de favoritos.");
            return false;
        }
    }

    //Adiciona a serie a lista deseja assistir
    public boolean adicionarSerieDesejaAssistir(Serie serie) {
        if (serie == null) {
            System.out.println("Erro: Não é possível adicionar uma série nula.");
            return false;
        }
        if (!desejaAssistir.contains(serie)) {
            desejaAssistir.add(serie);
            System.out.println("Série '" + serie.getNome() + "' adicionada à lista de deseja assistir.");
            return true;
        } else {
            System.out.println("Série '" + serie.getNome() + "' já está na lista de deseja assistir.");
            return false;
        }
    }

    //Métodos para remover séries das listas

    //Remove uma série da lista de séries assistidas.
    public boolean removerSerieAssistida(Serie serie) {
        if (serie == null) {
            System.out.println("Erro: Não é possível remover uma série nula.");
            return false;
        }
        if (assistidas.remove(serie)) {
            System.out.println("Série '" + serie.getNome() + "' removida da lista de assistidas.");
            return true;
        } else {
            System.out.println("Série '" + serie.getNome() + "' não encontrada na lista de assistidas.");
            return false;
        }
    }

    //Remove uma serie da lista de favoritos.
    public boolean removerSerieFavorita(Serie serie) {
        if (serie == null) {
            System.out.println("Erro: Não é possível remover uma série nula.");
            return false;
        }
        if (favoritas.remove(serie)) {
            System.out.println("Série '" + serie.getNome() + "' removida da lista de favoritos.");
            return true;
        } else {
            System.out.println("Série '" + serie.getNome() + "' não encontrada na lista de favoritos.");
            return false;
        }
    }

    //Remove uma série da lista de séries que deseja assistir.
    public boolean removerSerieDesejaAssistir(Serie serie) {
        if (serie == null) {
            System.out.println("Erro: Não é possível remover uma série nula.");
            return false;
        }
        if (desejaAssistir.remove(serie)) {
            System.out.println("Série '" + serie.getNome() + "' removida da lista de deseja assistir.");
            return true;
        } else {
            System.out.println("Série '" + serie.getNome() + "' não encontrada na lista de deseja assistir.");
            return false;
        }
    }

    //Métodos para exibir as listas

    //Exibe todas as series na lista de assistidas.
    public void mostrarListaAssistidas(boolean exibirDetalhes) {
        System.out.println("\n--- Minhas Séries Assistidas ---");
        if (assistidas.isEmpty()) {
            System.out.println("Nenhuma série assistida encontrada.");
            return;
        }
        for (int i = 0; i < assistidas.size(); i++) {
            System.out.println((i + 1) + ". " + (exibirDetalhes ? assistidas.get(i).toString() : assistidas.get(i).toShortString()));
        }
    }

    //Exibe todas as series na lista de favoritos.
    public void mostrarListaFavoritas(boolean exibirDetalhes) {
        System.out.println("\n--- Minhas Séries Favoritas ---");
        if (favoritas.isEmpty()) {
            System.out.println("Nenhuma série favorita encontrada.");
            return;
        }
        for (int i = 0; i < favoritas.size(); i++) {
            System.out.println((i + 1) + ". " + (exibirDetalhes ? favoritas.get(i).toString() : favoritas.get(i).toShortString()));
        }
    }

    //Exibe todas as séries na lista de séries que deseja assistir.
    public void mostrarListaDesejaAssistir(boolean exibirDetalhes) {
        System.out.println("\n--- Minhas Séries Que Desejo Assistir ---");
        if (desejaAssistir.isEmpty()) {
            System.out.println("Nenhuma série na lista de deseja assistir encontrada.");
            return;
        }
        for (int i = 0; i < desejaAssistir.size(); i++) {
            System.out.println((i + 1) + ". " + (exibirDetalhes ? desejaAssistir.get(i).toString() : desejaAssistir.get(i).toShortString()));
        }
    }

    //Metodos para ordenar as listas

    //Ordena a lista de séries por ordem alfabética do nome.
    public void ordenarListaPorNome(List<Serie> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Lista vazia ou nula para ordenação por nome.");
            return;
        }
        Collections.sort(lista);
        System.out.println("Lista ordenada por nome alfabeticamente.");
    }

    //Ordena a lista de séries pela nota geral (decrescente).
    public void ordenarListaPorNotaGeral(List<Serie> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Lista vazia ou nula para ordenação por nota.");
            return;
        }
        // Ordena pela nota geral (do maior para o menor)
        Collections.sort(lista, (s1, s2) -> {
            // Trata séries com nota nula
            if (s1.getNotaGeral() == null && s2.getNotaGeral() == null) return 0;
            if (s1.getNotaGeral() == null) return 1; // Coloca séries sem nota no final
            if (s2.getNotaGeral() == null) return -1; // Coloca séries sem nota no final
            return Double.compare(s2.getNotaGeral(), s1.getNotaGeral()); // Ordem decrescente
        });
        System.out.println("Lista ordenada por nota geral (maior para menor).");
    }

    //Ordena a lista de séries pelo estado (Ex: Running, Ended, Canceled).
    public void ordenarListaPorEstado(List<Serie> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Lista vazia ou nula para ordenação por estado.");
            return;
        }
        // Define uma ordem personalizada para os estados
        Collections.sort(lista, (s1, s2) -> {
            int ordem1 = getOrdemStatus(s1.getStatus());
            int ordem2 = getOrdemStatus(s2.getStatus());
            return Integer.compare(ordem1, ordem2);
        });
        System.out.println("Lista ordenada por estado (Running, Ended, etc.).");
    }

    private int getOrdemStatus(String status) {
        if (status == null) return 99; // Coloca status nulo no final
        return switch (status) {
            case "Running" -> 1;
            case "Ended" -> 2;
            case "To Be Determined" -> 3;
            case "In Development" -> 4;
            case "Canceled" -> 5;
            default -> 10; // Outros status
        };
    }

    //Ordena a lista de series pela data de estreia
    public void ordenarListaPorDataEstreia(List<Serie> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("Lista vazia ou nula para ordenação por data de estreia.");
            return;
        }
        // Ordena pela data de estreia (mais antiga para a mais recente)
        Collections.sort(lista, (s1, s2) -> {
            // Trata séries sem data de estreia
            if (s1.getDataEstreia() == null && s2.getDataEstreia() == null) return 0;
            if (s1.getDataEstreia() == null) return 1; // Coloca series sem data no final
            if (s2.getDataEstreia() == null) return -1;
            return s1.getDataEstreia().compareTo(s2.getDataEstreia());
        });
        System.out.println("Lista ordenada por data de estreia (mais antiga para mais recente).");
    }

    //getters
    public List<Serie> getFavoritas() {
        return favoritas;
    }
    public List<Serie> getAssistidas() {
        return assistidas;
    }
    public List<Serie> getDesejaAssistir() {
        return desejaAssistir;
    }

    //setters
    public void setFavoritas(List<Serie> favoritas) {
        this.favoritas = favoritas != null ? favoritas : new ArrayList<>();
    }
    public void setAssistidas(List<Serie> assistidas) {
        this.assistidas = assistidas != null ? assistidas : new ArrayList<>();
    }
    public void setDesejaAssistir(List<Serie> desejaAssistir) {
        this.desejaAssistir = desejaAssistir != null ? desejaAssistir : new ArrayList<>();
    }
}
