package modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiaClima {
    @JsonProperty("tempmax")
    private double tempMaxima;
    @JsonProperty("tempmin")
    private double tempMinima;
    @JsonProperty("precip")
    private double precipitacao;

    // Getters
    public double getTempMaxima() { return tempMaxima; }
    public double getTempMinima() { return tempMinima; }
    public double getPrecipitacao() { return precipitacao; }
}