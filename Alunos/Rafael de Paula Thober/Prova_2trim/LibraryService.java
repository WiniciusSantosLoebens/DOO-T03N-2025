import java.util.*;

public class LibraryService {

    private final Map<String, Set<Integer>> favorites = new HashMap<>();
    private final Map<String, Set<Integer>> watched = new HashMap<>();
    private final Map<String, Set<Integer>> toWatch = new HashMap<>();
    private final TvMazeClient client = new TvMazeClient();

    public Set<Integer> getList(String nickname, ListName listName) {
        return switch (listName) {
            case FAVORITES -> favorites.computeIfAbsent(nickname, k -> new HashSet<>());
            case WATCHED -> watched.computeIfAbsent(nickname, k -> new HashSet<>());
            case TO_WATCH -> toWatch.computeIfAbsent(nickname, k -> new HashSet<>());
        };
    }

    public void addToList(String nickname, int showId, ListName listName) {
        getList(nickname, listName).add(showId);
    }

    public void removeFromList(String nickname, int showId, ListName listName) {
        getList(nickname, listName).remove(showId);
    }

    public boolean isInList(String nickname, int showId, ListName listName) {
        return getList(nickname, listName).contains(showId);
    }

    public List<Show> getFullList(String nickname, ListName listName) {
        Set<Integer> ids = getList(nickname, listName);
        List<Show> shows = new ArrayList<>();

        for (int id : ids) {
            Show show = client.getShowById(id);
            if (show != null) {
                shows.add(show);
            }
        }

        return shows;
    }

    public List<Show> sortByName(List<Show> list) {
        list.sort(Comparator.comparing(Show::getName, String.CASE_INSENSITIVE_ORDER));
        return list;
    }

    public List<Show> sortByRating(List<Show> list) {
        list.sort(Comparator.comparingDouble(Show::getRating).reversed());
        return list;
    }

    public List<Show> sortByStatus(List<Show> list) {
        list.sort(Comparator.comparing(Show::getStatus));
        return list;
    }

    public List<Show> sortByPremiered(List<Show> list) {
        list.sort(Comparator.comparing(Show::getPremiered, Comparator.nullsLast(String::compareTo)));
        return list;
    }
}
