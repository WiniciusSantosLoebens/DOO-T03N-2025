package Aula12.Aula12;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main{
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Clima {
        public int queryCost;
        public double latitude;
        public double longitude;
        public String resolvedAddress;
        public String address;
        public String timezone;
        public double tzoffset;
        public String description;
        public List<Dia> days;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Dia {
        public List<Hora> hours;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Hora {
        public double temp;
        public double humidity;
        public double precipprob;
        public double windspeed;
        public double winddir;
        public String conditions;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        boolean close = true;

        while (close) {
            System.out.println("[1]- Ver Clima da minha cidade\n[0]- Sair");
            int option = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (option) {
                case 1:
                    System.out.println("Qual sua cidade?");
                    String cidade = sc.nextLine();

                    if (!cidade.isEmpty()) {
                        String key = props.getProperty("api.key");

                        String url = String.format(
                                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s/today?unitGroup=metric&lang=pt&include=hours&key=%s&contentType=json",
                                cidade, key
                        );

                        try {
                            HttpClient client = HttpClient.newHttpClient();
                            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
                            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                            String json = response.body();

                            ObjectMapper mapper = new ObjectMapper();
                            Clima clima = mapper.readValue(json, Clima.class);

                            List<Hora> horas = clima.days.get(0).hours;

                            int horaAtual = LocalTime.now().getHour();
                            if (horaAtual >= horas.size()) horaAtual = horas.size() - 1;

                            Hora inf = horas.get(horaAtual);
                            Double probabilidadeChuva = inf.precipprob;
                            Double umidade = inf.humidity;

                            String linhaCSV =(
                                            "\nTemperatura: " + inf.temp + ","
                                            + "\nUmidade: " + umidade+ "%,"
                                            + "\nProbabilidade de precipitacao: " + probabilidadeChuva + "%,"
                                            + "\nVelocidade vento: " + inf.windspeed + "km/h,"
                                            + "\nDirecao vento: " + inf.winddir + ","
                                            + "\nCondicao do tempo: " + inf.conditions);

                            System.out.println("Clima agora (" + horaAtual + "hr): " + linhaCSV);

                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Informe a cidade corretamente.");
                    }
                    break;

                case 0:
                    System.out.println("Obrigado por nos visitar. Tenha um ótimo dia!");
                    close = false;
                    break;
            }
        }
    }
}
