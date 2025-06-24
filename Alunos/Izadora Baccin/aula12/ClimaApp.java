import org.json.JSONObject;
import org.json.JSONException;
import java.util.Scanner;
import java.net.http.HttpConnectTimeoutException;
import java.net.UnknownHostException;

public class ClimaApp {

    public static void executar() {
        Scanner scanner = new Scanner(System.in);
        String cidade;
        String estado;
        String local;
        String apiKey = "VV8CX2RZJMV3CYAV4UJ4UQS3L";

        boolean entradaValida;
        do {
            System.out.print("Digite a cidade: ");
            cidade = scanner.nextLine().trim();

            System.out.print("Digite a sigla do estado (ex: SP, RJ): ");
            estado = scanner.nextLine().trim();

            if (cidade.isEmpty() || estado.isEmpty()) {
                System.out.println("❌ Cidade e/ou estado não podem estar vazios. Por favor, tente novamente.");
                entradaValida = false;
            } else if (estado.length() != 2 || !estado.matches("[a-zA-Z]+")) {
                System.out.println("❌ Sigla do estado inválida. Por favor, digite duas letras (ex: SP, RJ).");
                entradaValida = false;
            } else {
                entradaValida = true;
            }
        } while (!entradaValida);

        local = cidade + "," + estado;

        try {
            ClienteApiClima apiClient = new ClienteApiClima(apiKey);
            String jsonResponse = apiClient.obterDadosClima(local);

            JSONObject json = new JSONObject(jsonResponse);
            if (!json.has("days") || json.getJSONArray("days").isEmpty()) {
                System.out.println("❌ Dados de clima para o local especificado não encontrados. Verifique a cidade e o estado.");
                return;
            }

            JSONObject dia = json.getJSONArray("days").getJSONObject(0);
            JSONObject atual = json.getJSONObject("currentConditions");

            System.out.println("\n📍 Local: " + cidade + " - " + estado.toUpperCase());
            System.out.println("🌡 Temperatura atual: " + atual.optDouble("temp", Double.NaN) + "°C");
            System.out.println("📈 Máxima: " + dia.optDouble("tempmax", Double.NaN) + "°C");
            System.out.println("📉 Mínima: " + dia.optDouble("tempmin", Double.NaN) + "°C");
            System.out.println("💧 Umidade: " + atual.optDouble("humidity", Double.NaN) + "%");
            System.out.println("🌥 Condição do tempo: " + atual.optString("conditions", "N/A"));
            System.out.println("🌧 Precipitação: " + atual.optDouble("precip", 0.0) + " mm");
            System.out.println("💨 Vento: " + atual.optDouble("windspeed", Double.NaN) + " km/h, direção " + atual.optDouble("winddir", Double.NaN) + "°");

        } catch (HttpConnectTimeoutException e) {
            System.out.println("❌ Erro de conexão: Tempo limite excedido ao tentar conectar ao servidor. Verifique sua conexão com a internet.");
        } catch (UnknownHostException e) {
            System.out.println("❌ Erro de rede: Não foi possível resolver o endereço do servidor. Verifique sua conexão com a internet.");
        } catch (JSONException e) { // Capturar JSONException antes de RuntimeException ou Exception
            System.out.println("❌ Erro ao processar os dados do clima (JSON inválido). Pode ser um problema temporário da API ou dados inesperados.");
            System.out.println("Detalhes técnicos: " + e.getMessage());
        } catch (RuntimeException e) { // Captura a exceção lançada por ClienteApiClima
            System.out.println("❌ Erro na requisição da API: " + e.getMessage() + ". Verifique se a cidade e estado estão corretos.");
        } catch (Exception e) {
            System.out.println("❌ Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}

