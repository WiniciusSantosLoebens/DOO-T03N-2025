import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteApiClima {

    private static final String API_BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private final String apiKey;

    public ClienteApiClima(String apiKey) {
        this.apiKey = apiKey;
    }

    public String obterDadosClima(String location) throws Exception {
        String urlStr = API_BASE_URL
                + location.replace(" ", "%20")
                + "/today?unitGroup=metric&key=" + apiKey + "&include=current";

        HttpClient client = HttpClient.newHttpClient();
        URI uri = new URI(urlStr);

        HttpRequest request = HttpRequest.newBuilder(uri)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Erro na requisição da API: " + response.statusCode() + " - " + response.body());
        }
    }
}