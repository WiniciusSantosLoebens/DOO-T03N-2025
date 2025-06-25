package modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaClima {
    @JsonProperty("resolvedAddress")
    private String enderecoResolvido;

    @JsonProperty("days")
    private List<DiaClima> dias;

    @JsonProperty("currentConditions")
    private CondicoesAtuais condicoesAtuais;

    // Getters
    public String getEnderecoResolvido() { return enderecoResolvido; }
    public List<DiaClima> getDias() { return dias; }
    public CondicoesAtuais getCondicoesAtuais() { return condicoesAtuais; }
}