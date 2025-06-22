
import java.util.*;

public class ConsoleApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static String nickname;
    private static final TvMazeClient client = new TvMazeClient();
    private static final LibraryService service = new LibraryService();

    public static void main(String[] args) {
        System.out.print("Digite seu apelido: ");
        nickname = scanner.nextLine().trim();

        int option;
        do {
            printMenu();
            option = getIntInput("» ");
            switch (option) {
                case 1 ->
                    searchSeries();
                case 2 ->
                    showList("Favoritas", service.getList(nickname, ListName.FAVORITES));
                case 3 ->
                    showList("Assistidas", service.getList(nickname, ListName.WATCHED));
                case 4 ->
                    showList("Para assistir", service.getList(nickname, ListName.TO_WATCH));
                case 5 ->
                    sortListMenu();
                case 0 ->
                    System.out.println("Encerrando...");
                default ->
                    System.out.println("Opção inválida!");
            }
        } while (option != 0);
    }

    private static void printMenu() {
        System.out.println("\nOlá, " + nickname + "! Escolha uma opção:");
        System.out.println("1) Buscar série por nome");
        System.out.println("2) Ver lista de favoritas");
        System.out.println("3) Ver lista de assistidas");
        System.out.println("4) Ver lista de para assistir");
        System.out.println("5) Ordenar listas");
        System.out.println("0) Sair");
    }

    private static void searchSeries() {
        System.out.print("Digite o nome da série: ");
        String name = scanner.nextLine();

        List<Show> results = client.searchShows(name);
        if (results.isEmpty()) {
            System.out.println("Nenhuma série encontrada.");
            return;
        }

        System.out.println("\nResultados encontrados:");
        for (int i = 0; i < results.size(); i++) {
            Show show = results.get(i);
            System.out.println((i + 1) + ") " + show.toStringCompact());
        }

        int choice = getIntInput("Escolha um número para ver detalhes (0 para voltar): ");
        if (choice > 0 && choice <= results.size()) {
            Show selected = results.get(choice - 1);
            System.out.println("\n🔎 Detalhes:");
            System.out.println(selected.toStringDetail());
            manageShow(selected);
        }
    }

    private static void manageShow(Show show) {
        System.out.println("Deseja adicionar ou remover de alguma lista?");
        System.out.println("1) Favoritos");
        System.out.println("2) Já assistidas");
        System.out.println("3) Quero assistir");
        System.out.println("0) Voltar");

        System.out.print("Digite os números das listas separados por vírgula (ex: 1,3): ");
        String input = scanner.nextLine().trim();

        if (input.equals("0")) {
            return;
        }

        String[] parts = input.split(",");
        for (String part : parts) {
            int opt;
            try {
                opt = Integer.parseInt(part.trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido: " + part);
                continue;
            }

            ListName list = switch (opt) {
                case 1 ->
                    ListName.FAVORITES;
                case 2 ->
                    ListName.WATCHED;
                case 3 ->
                    ListName.TO_WATCH;
                default ->
                    null;
            };

            if (list == null) {
                System.out.println("Opção inválida: " + part);
                continue;
            }

            boolean exists = service.isInList(nickname, show.getId(), list);
            if (exists) {
                service.removeFromList(nickname, show.getId(), list);
                System.out.println("Removido da lista " + list.getLabel());
            } else {
                service.addToList(nickname, show.getId(), list);
                System.out.println("Adicionado à lista " + list.getLabel());
            }
        }
    }

    private static void showList(String title, Set<Integer> ids) {
        System.out.println("\n📂 Lista: " + title);
        if (ids.isEmpty()) {
            System.out.println("(lista vazia)");
            return;
        }

        for (int id : ids) {
            Show show = client.getShowById(id);
            if (show != null) {
                System.out.println("• " + show.getName());
            }
        }
    }

    private static void sortListMenu() {
        System.out.println("Qual lista deseja ordenar?");
        System.out.println("1) Favoritos");
        System.out.println("2) Assistidos");
        System.out.println("3) Para assistir");
        int opt = getIntInput("» ");

        Set<Integer> ids;
        ListName listName;

        switch (opt) {
            case 1 -> {
                listName = ListName.FAVORITES;
                ids = service.getList(nickname, listName);
            }
            case 2 -> {
                listName = ListName.WATCHED;
                ids = service.getList(nickname, listName);
            }
            case 3 -> {
                listName = ListName.TO_WATCH;
                ids = service.getList(nickname, listName);
            }
            default -> {
                System.out.println("Opção inválida");
                return;
            }
        }

        List<Show> shows = new ArrayList<>();
        for (int id : ids) {
            Show show = client.getShowById(id);
            if (show != null) {
                shows.add(show);
            }
        }

        System.out.println("Escolha o critério de ordenação:");
        System.out.println("1) Ordem alfabética do nome");
        System.out.println("2) Nota geral");
        System.out.println("3) Estado da série");
        System.out.println("4) Data de estreia");
        int sortOpt = getIntInput("» ");

        Comparator<Show> comparator = null;

        switch (sortOpt) {
            case 1 -> comparator = Comparator.comparing(Show::getName, String.CASE_INSENSITIVE_ORDER);
            case 2 -> comparator = Comparator.comparingDouble(Show::getRating).reversed();
            case 3 -> comparator = Comparator.comparing(Show::getStatus, String.CASE_INSENSITIVE_ORDER);
            case 4 -> comparator = Comparator.comparing(Show::getPremiered, Comparator.nullsLast(Comparator.naturalOrder()));
            default -> {
                System.out.println("Critério inválido");
                return;
            }
        }

        shows.sort(comparator);

        System.out.println("📋 Lista ordenada:");
        for (Show show : shows) {
            System.out.println("• " + show.getName());
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
