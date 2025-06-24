// Importando as bibliotecas necessárias
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    private static final String API_KEY = "CEZP4SKC42SS6EK8NXSQM5YJV";
    

    private static final String API_BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static void main(String[] args) throws IOException, InterruptedException {

        // Usando Scanner para obter a entrada do usuário de forma segura.
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Digite o nome da cidade para consultar o clima: ");
            String cidade = scanner.nextLine();

            // Codifica o nome da cidade para ser usado em uma URL.
            String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

            // Monta a URL completa para a requisição
            String urlCompleta = String.format(
                "%s%s?unitGroup=metric&key=%s&contentType=json&lang=pt",
                API_BASE_URL, cidadeCodificada, API_KEY
            );
            
            // Cria um cliente HTTP moderno (disponível desde o Java 11)
            HttpClient cliente = HttpClient.newHttpClient();
            
            // Cria um objeto de requisição com a URL
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(urlCompleta))
                    .build();

            // Envia a requisição e obtém a resposta.
            // HttpResponse.BodyHandlers.ofString() converte o corpo da resposta em uma String.
            HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

            // Verifica se a requisição foi bem-sucedida (código de status 200)
            if (resposta.statusCode() == 200) {
                // Analisa (parse) a String JSON da resposta
                parseAndDisplayWeather(resposta.body());
            } else {
                System.out.println("Erro ao buscar dados do clima. Código de status: " + resposta.statusCode());
                System.out.println("Mensagem: " + resposta.body());
            }
        } // O 'try-with-resources' fecha o 'scanner' automaticamente.
    }

    /**
     * Analisa o JSON retornado pela API e exibe as informações formatadas.
     * @param jsonResponse String contendo a resposta da API em formato JSON.
     */
    private static void parseAndDisplayWeather(String jsonResponse) {
        // Cria um objeto JSON a partir da string de resposta
        JSONObject jsonObj = new JSONObject(jsonResponse);

        // Extrai o objeto 'currentConditions' que contém os dados do momento
        JSONObject currentConditions = jsonObj.getJSONObject("currentConditions");

        // Extrai o array 'days' que contém a previsão diária (usaremos o primeiro item, que é hoje)
        JSONArray days = jsonObj.getJSONArray("days");
        JSONObject today = days.getJSONObject(0);
        
        // Extraindo as informações necessárias
        String resolvedAddress = jsonObj.getString("resolvedAddress");
        double tempAtual = currentConditions.getDouble("temp");
        String condicao = currentConditions.getString("conditions");
        double tempMax = today.getDouble("tempmax");
        double tempMin = today.getDouble("tempmin");
        double umidade = currentConditions.getDouble("humidity");
        double precipitacao = currentConditions.optDouble("precip", 0.0); // optDouble para caso não chova (valor padrão 0.0)
        double velVento = currentConditions.getDouble("windspeed");
        double dirVento = currentConditions.getDouble("winddir");

        // Exibindo as informações de forma organizada
        System.out.println("\nClima e Tempo em: " + resolvedAddress);
        System.out.println("--------------------------------------------------");
        System.out.printf("-> Temperatura Atual: %.1f °C\n", tempAtual);
        System.out.printf("-> Condição do Tempo: %s\n", condicao);
        System.out.printf("-> Máxima para hoje: %.1f °C\n", tempMax);
        System.out.printf("-> Mínima para hoje: %.1f °C\n", tempMin);
        System.out.printf("-> Umidade do Ar: %.1f %%\n", umidade);
        System.out.printf("-> Precipitação: %.1f mm\n", precipitacao);
        System.out.printf("-> Vento: %.1f km/h, direção %.0f° (%s)\n", velVento, dirVento, windDirectionFromDegrees(dirVento));
        System.out.println("--------------------------------------------------");
    }
    
    /**
     * Converte a direção do vento de graus para uma sigla de ponto cardeal.
     * @param degrees Direção do vento em graus (0-360).
     * @return A sigla do ponto cardeal (N, NE, E, SE, S, SW, W, NW).
     */
    private static String windDirectionFromDegrees(double degrees) {
        String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW", "N"};
        return directions[(int)Math.round((degrees % 360) / 45)];
    }
}