package servico;

import com.fasterxml.jackson.databind.ObjectMapper;
import modelo.RespostaClima;

import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ClienteApiClima {
    private static final String CHAVE_API = carregarChaveApi();

    private static final String URL_BASE = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s?unitGroup=metric&include=current&key=%s&contentType=json";

    private final HttpClient cliente = HttpClient.newHttpClient();
    private final ObjectMapper mapeadorJson = new ObjectMapper();
    
    
    private static String carregarChaveApi() {
        try (InputStream input = ClienteApiClima.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.err.println("ERRO: Desculpe, não foi possível encontrar o arquivo config.properties");
                return null;
            }
            prop.load(input);
            return prop.getProperty("api.key");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    

    public RespostaClima obterClimaParaCidade(String cidade) {
    	 if (CHAVE_API == null || CHAVE_API.isEmpty()) {
             System.err.println("ERRO: A chave da API não foi carregada. Verifique o arquivo config.properties.");
             return null;
         }

        try {
            String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
            String urlCompleta = String.format(URL_BASE, cidadeCodificada, CHAVE_API);

            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(urlCompleta))
                    .GET()
                    .build();

            HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

            if (resposta.statusCode() == 200) {
                return mapeadorJson.readValue(resposta.body(), RespostaClima.class);
            } else {
                System.err.println("Erro na API: " + resposta.statusCode() + " - " + resposta.body());
                return null;
            }

        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao buscar os dados do tempo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}