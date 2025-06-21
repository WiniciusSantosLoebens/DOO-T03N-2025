package org.fag.ui;

import org.fag.api.SeriesMapper;
import java.util.Scanner;
import org.fag.managers.*;
import org.fag.model.Serie;

public class UserInterface {

    private final SeriesMapper serieMapper;
    private final FavoritesManager favorites;
    private final WatchedManager watched;
    private final WatchlistManager watchlist;
    private final Scanner scanner;
    public String titulo =
            " _____      _       ____   _____   _   _        \n" +
                    " |  ___|    / \\     / ___| |  ___| | | (_) __  __\n" +
                    " | |_      / _ \\   | |  _  | |_    | | | | \\ \\/ /\n" +
                    " |  _|    / ___ \\  | |_| | |  _|   | | | |  >  < \n" +
                    " |_|     /_/   \\_\\  \\____| |_|     |_| |_| /_/\\_\\";

    // Passar os componentes necessários para a classe que gerencia a interface

    public UserInterface(SeriesMapper serieMapper, FavoritesManager favorites, WatchedManager watched, WatchlistManager watchlist, Scanner scanner) {
        this.serieMapper = serieMapper;
        this.favorites = favorites;
        this.watched = watched;
        this.watchlist = watchlist;
        this.scanner = new Scanner(System.in);
    }

    private void displayMenu(){
        System.out.println("" +
                "[1] - Buscar series" +
                "[2] - Series Favoritadas" +
                "[3] - Series Vistas" +
                "[4] - Series para ver depois" +
                "[5] - Lorem Ipsum Dolor" +
                "[6] - Lorem Ipsum Dolor" +
                "[7] - Lorem Ipsum Dolor");
    }

    private int getUserChoice(){
        while(!scanner.hasNextInt()){
            System.out.println("Entrada inválida. Por favor, digite um nũmero");
            scanner.next();
            System.out.println("Escolha uma opção.");
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private void handleSerieSearch(){
        System.out.print("Digite o nome da série para buscar: ");
        String seriesName = scanner.nextLine();

        try {
            serieName = seriesName.replaceAll("[\\p{P}\\p{S}]", "");
            String apiURL = "https://api.tvmaze.com/search/shows?q=" + serieName;
            String jsonResponse = apiClient.searchSeriesByName(apiURL); // Chama o API client
            List<Serie> foundSeries = seriesParser.mapJsonToSeriesList(jsonResponse); // Mapeia o JSON
            if (foundSeries.isEmpty()) {
                System.out.println("Nenhuma série encontrada com este nome.");
            } else {
                System.out.println("\nSéries encontradas:");
                for (int i = 0; i < foundSeries.size(); i++) {
                    Serie s = foundSeries.get(i);
                    System.out.println((i + 1) + ". " + s.getNome() + " (" + s.getDataEstreia() + ")");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar séries: " + e.getMessage());
            // Logar o erro completo aqui
        }
    }
    // Disponibilizar a busca de séries por nome;
    // mudar favoritos e listas;
    // exibir as listas quando solicitado;
        // listas exibidas podem ser ordenadas;

}


























