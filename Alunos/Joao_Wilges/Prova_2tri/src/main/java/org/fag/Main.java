package org.fag; // Assuming this is your base package

import org.fag.api.ApiClient;
import org.fag.api.SeriesMapper;
import org.fag.managers.FavoritesManager;
import org.fag.managers.WatchedManager;
import org.fag.managers.WatchlistManager;
import org.fag.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        // Initialize core components
        SeriesMapper seriesMapper = new SeriesMapper();

        // Initialize list managers
        FavoritesManager favoritesManager = new FavoritesManager();
        WatchedManager watchedManager = new WatchedManager();
        WatchlistManager watchlistManager = new WatchlistManager();

        // Initialize the UserInterface, passing all dependencies
        UserInterface userInterface = new UserInterface(
                seriesMapper,
                favoritesManager,
                watchedManager,
                watchlistManager
        );

        // Start the application's user interface
        userInterface.start();
    }
}