import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException; // Importar URISyntaxException
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class TvMazeAPI {

    // Cliente HTTP para fazer as requisições
    private static final HttpClient client = HttpClient.newHttpClient();

    // Busca séries na API da TVMaze por nome e retorna o resultado como um JSONArray.

    public static Optional<JSONArray> buscarSeriesPorNome(String nomeBuscado) {
        if (nomeBuscado == null || nomeBuscado.trim().isEmpty()) {
            System.out.println("A busca requer um nome de série válido.");
            return Optional.empty();
        }

        try {
            String nomeFormatado = URLEncoder.encode(nomeBuscado.trim(), StandardCharsets.UTF_8);
            URI url = new URI("https://api.tvmaze.com/search/shows?q=" + nomeFormatado); // Linha 32

            HttpRequest request = HttpRequest.newBuilder(url)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return Optional.of(new JSONArray(response.body()));
            } else {
                System.err.println("Erro na requisição para '" + nomeBuscado + "'. Código de resposta: " + response.statusCode());
                // Mensagem de erro da API
                if (response.body() != null && !response.body().isEmpty()) {
                    System.err.println("Mensagem de erro da API: " + response.body());
                }
                return Optional.empty();
            }

        } catch (URISyntaxException e) { // Captura URISyntaxException especificamente
            System.err.println("Erro de sintaxe na URI ao buscar série por nome ('" + nomeBuscado + "'): " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar série por nome ('" + nomeBuscado + "'): " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    //Busca uma série na API da TVMaze por ID e retorna o resultado como um JSONObject.

    public static Optional<JSONObject> buscarSeriePorId(int id) {
        try {
            URI url = new URI("https://api.tvmaze.com/shows/" + id); // Linha 67

            HttpRequest request = HttpRequest.newBuilder(url)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return Optional.of(new JSONObject(response.body()));
            } else if (response.statusCode() == 404) {
                System.out.println("Série com ID " + id + " não encontrada na API.");
                return Optional.empty();
            } else {
                System.err.println("Erro na requisição para ID " + id + ". Código de resposta: " + response.statusCode());
                // Mensagem de erro da API, se disponível
                if (response.body() != null && !response.body().isEmpty()) {
                    System.err.println("Mensagem de erro da API: " + response.body());
                }
                return Optional.empty();
            }

        } catch (URISyntaxException e) { // Captura URISyntaxException especificamente
            System.err.println("Erro de sintaxe na URI ao buscar série por ID (" + id + "): " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar série por ID (" + id + "): " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}