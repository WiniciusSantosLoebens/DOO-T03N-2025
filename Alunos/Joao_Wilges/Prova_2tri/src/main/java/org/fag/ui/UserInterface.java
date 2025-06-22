package org.fag.ui;

import org.fag.api.ApiClient;
import org.fag.api.SeriesMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.fag.managers.FavoritesManager;
import org.fag.managers.SerieListManagers;
import org.fag.managers.WatchedManager;
import org.fag.managers.WatchlistManager;
import org.fag.model.Serie;
import java.util.Collections;

public class UserInterface {

    private final SeriesMapper serieMapper;
    private final FavoritesManager favorites;
    private final WatchedManager watched;
    private final WatchlistManager watchlist;
    private final Scanner scanner;
    private String userName; // Adicionado: atributo para armazenar o nome do usuário

    public String titulo =
            " _____      _       ____   _____   _   _        \n" +
                    " |  ___|    / \\     / ___| |  ___| | | (_) __  __\n" +
                    " | |_      / _ \\   | |  _  | |_    | | | | \\ \\/ /\n" +
                    " |  _|    / ___ \\  | |_| | |  _|   | | | |  >  < \n" +
                    " |_|     /_/   \\_\\  \\____| |_|     |_| |_| /_/\\_\\";

    public UserInterface(SeriesMapper serieMapper, FavoritesManager favorites, WatchedManager watched, WatchlistManager watchlist) {
        this.serieMapper = serieMapper;
        this.favorites = favorites;
        this.watched = watched;
        this.watchlist = watchlist;
        this.scanner = new Scanner(System.in);
    }

    // Adicionado: Getter para o nome do usuário (usado pelo Main para salvar)
    public String getUserName() {
        return userName;
    }

    // Adicionado: Setter para o nome do usuário (usado pelo Main para carregar)
    public void setUserName(String userName) {
        this.userName = userName;
    }

    private void displayMenu(){
        // Removido o prompt de "Bem-vindo" daqui. Será no método start().
        System.out.println(titulo);
        System.out.println("\n--- Gerenciador de Séries ---");
        System.out.println("[1] - Buscar séries");
        System.out.println("[2] - Séries Favoritadas");
        System.out.println("[3] - Séries Vistas");
        System.out.println("[4] - Séries para ver depois");
        System.out.println("[0] - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private int getUserChoice(){
        while(!scanner.hasNextInt()){
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next();
            System.out.print("Escolha uma opção: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private List<Serie> serieSearch(){
        System.out.print("Digite o nome da série para buscar: ");
        String serieName = scanner.nextLine();

        try {
            serieName = serieName.replaceAll("[^\\p{L}\\p{N}\\s]", "");
            // Correção: URLEncoder.encode espera um String para o charset
            String encodedSerieName = URLEncoder.encode(serieName, StandardCharsets.UTF_8.toString());
            String apiURL = "https://api.tvmaze.com/search/shows?q=" + encodedSerieName;
            String jsonResponse = ApiClient.searchSeriesByName(apiURL);
            List<Serie> foundSeries = serieMapper.mapJsonToSeriesList(jsonResponse);
            if (foundSeries.isEmpty()) {
                System.out.println("Nenhuma série encontrada com este nome.");
                return Collections.emptyList();
            } else {
                System.out.println("\nSéries encontradas:");
                // Melhorando a exibição para a que mostrava ID e data de estreia
                for (int i = 0; i < foundSeries.size(); i++) {
                    Serie s = foundSeries.get(i);
                    System.out.println((i + 1) + ". " + s.getNome() + " (ID: " + s.getId() + ") - " + s.getDataEstreia());
                }
                return foundSeries;
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar séries: " + e.getMessage());
            return Collections.emptyList();
        }
    }
    private void addSerieFromSearchResults(List<Serie> searchResults, SerieListManagers targetManager) {
        if (searchResults.isEmpty()) {
            System.out.println("Não há séries nos resultados da busca para adicionar.");
            return;
        }

        System.out.println("\n--- Adicionar Série por ID ---");
        System.out.print("Digite o ID da série que deseja adicionar: ");
        String serieId = scanner.nextLine();

        Optional<Serie> serieToAdd = searchResults.stream()
                .filter(s -> s.getId().equals(serieId))
                .findFirst();

        if (serieToAdd.isPresent()) {
            targetManager.addSerie(serieToAdd.get());
            System.out.println("Série '" + serieToAdd.get().getNome() + "' adicionada à lista com sucesso!");
        } else {
            System.out.println("ID de série não encontrado nos resultados da busca. Verifique o ID e tente novamente.");
        }
    }

    private void manageSerieList(List<Serie> seriesContext, SerieListManagers manager) {
        // A opção 1 "Adicionar série (dos resultados da busca)" usa 'seriesContext'.
        // Se 'seriesContext' veio de uma busca, ela funcionará.
        // Se 'seriesContext' for a própria lista do manager (opções 2,3,4 do menu principal),
        // essa opção ainda funciona, permitindo adicionar da lista atual para ela mesma (sem efeito)
        // ou adicionar uma série que o usuário encontre o ID nela.
        System.out.println("\n--- Gerenciar Séries ---");
        System.out.println("[1] - Adicionar série (dos resultados da busca)");
        System.out.println("[2] - Remover série da lista (por ID)");
        System.out.println("[3] - Exibir lista atual");
        System.out.println("[4] - Ordenar lista atual");
        System.out.println("[0] - Voltar");
        System.out.print("Escolha uma opção: ");

        int choice = getUserChoice();
        switch (choice) {
            case 1 -> addSerieFromSearchResults(seriesContext, manager);
            case 2 -> {
                System.out.print("Digite o ID da série para remover: ");
                String serieIdRemove = scanner.nextLine();
                manager.removeSerie(serieIdRemove);
                System.out.println("Série removida (se encontrada).");
            }
            case 3 -> manager.showSerieList();
            case 4 -> orderListMenu(manager);
            case 0 -> { /* Voltar */ }
            default -> System.out.println("Opção inválida.");
        }
    }

    private void orderListMenu(SerieListManagers manager) {
        System.out.println("\n--- Ordenar Lista por ---");
        System.out.println("[1] - Nome (A-Z)");
        System.out.println("[2] - Nota");
        System.out.println("[3] - Status");
        System.out.println("[4] - Data de Estreia");
        System.out.println("[0] - Voltar");
        System.out.print("Escolha uma opção: ");

        int choice = getUserChoice();
        switch (choice) {
            case 1 -> { manager.orderByName(); System.out.println("Lista ordenada por nome."); }
            case 2 -> { manager.orderByRating(); System.out.println("Lista ordenada por nota."); }
            case 3 -> { manager.orderByStatus(); System.out.println("Lista ordenada por status."); }
            case 4 -> { manager.orderByPremiered(); System.out.println("Lista ordenada por data de estreia."); }
            case 0 -> { /* Voltar */ }
            default -> System.out.println("Opção inválida. Por favor, tente novamente.");
        }
        manager.showSerieList();
    }

    public void start() {
        // Mover a lógica de pedir o nome do usuário para cá, para que seja feita apenas uma vez
        if (this.userName == null || this.userName.isEmpty()) {
            System.out.print("Bem-vindo! Por favor, digite seu nome: ");
            this.userName = scanner.nextLine();
        }
        System.out.println("Olá, " + (this.userName != null ? this.userName : "Usuário") + "!");

        int choice;
        do {
            displayMenu(); // Agora displayMenu() apenas mostra as opções
            choice = getUserChoice();

            switch (choice) {
                case 1 -> { // Buscar séries
                    List<Serie> searchResults = serieSearch();
                    if (!searchResults.isEmpty()) {
                        System.out.println("\nSéries encontradas. O que deseja fazer?");
                        System.out.println("[1] Gerenciar essas séries em uma lista");
                        System.out.println("[0] Voltar ao menu principal");
                        System.out.print("Escolha: ");
                        int manageLists = getUserChoice();
                        switch (manageLists){
                            case 1 -> {
                                System.out.println("Qual lista você deseja gerenciar?");
                                System.out.println("[1] Favoritos");
                                System.out.println("[2] Assistidas");
                                System.out.println("[3] Para Ver Depois");
                                System.out.print("Escolha: ");
                                int listChoice = getUserChoice();
                                switch (listChoice) {
                                    case 1 -> manageSerieList(searchResults, favorites);
                                    case 2 -> manageSerieList(searchResults, watched);
                                    case 3 -> manageSerieList(searchResults, watchlist);
                                    default -> System.out.println("Opção de lista inválida.");
                                }
                            }
                            case 0 -> System.out.println("Voltando...");
                            default -> System.out.println("Opção inválida. Por favor, tente novamente.");

                        }

                    }
                }
                case 2 -> { // Gerenciar Favoritas
                    favorites.showSerieList();
                    manageSerieList(favorites.getSerieList(), favorites);
                }
                case 3 -> { // Gerenciar Vistas
                    watched.showSerieList();
                    manageSerieList(watched.getSerieList(), watched);
                }
                case 4 -> { // Gerenciar Para Ver Depois
                    watchlist.showSerieList();
                    manageSerieList(watchlist.getSerieList(), watchlist);
                }
                case 0 -> System.out.println("Saindo do programa. Até logo!");
                default -> System.out.println("Opção inválida. Por favor, tente novamente.");
            }
            System.out.println("\n----------------------------------\n");
        } while (choice != 0);
        scanner.close();
    }
}