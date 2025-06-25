package modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CondicoesAtuais {
    @JsonProperty("temp")
    private double temperatura;
    @JsonProperty("humidity")
    private double umidade;
    @JsonProperty("conditions")
    private String condicoes;
    @JsonProperty("windspeed")
    private double velocidadeVento;
    @JsonProperty("winddir")
    private double direcaoVento;
    @JsonProperty("icon")
    private String icone;

    // Getters
    public double getTemperatura() { return temperatura; }
    public double getUmidade() { return umidade; }
    public String getCondicoes() { return condicoes; }
    public double getVelocidadeVento() { return velocidadeVento; }
    public double getDirecaoVento() { return direcaoVento; }
    public String getIcone() { return icone; }
}