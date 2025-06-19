import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsultaMoedas {
    public static void main(String[] args) {
        try {
            String endpoint = "https://economia.awesomeapi.com.br/json/last/USD-BRL,EUR-BRL";
            URL url = new URL(endpoint);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int status = conexao.getResponseCode();
            System.out.println("Status da resposta: " + status);

            BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String inputLine;
            StringBuilder resposta = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                resposta.append(inputLine);
            }
            in.close();

            System.out.println("Resposta da API:");
            System.out.println(resposta.toString());

        } catch (Exception e) {
            System.out.println("Erro ao conectar com a API: " + e.getMessage());
        }
    }
}