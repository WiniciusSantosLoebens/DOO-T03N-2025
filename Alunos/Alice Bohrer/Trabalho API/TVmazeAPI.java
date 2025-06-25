package classes;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TVmazeAPI {

  //1- endereço fixo da API, após o ?q= vai o nome da serie escolhida
  //2- Objeto que faz as requisições pra internet.
  //3- Objeto que traduz os dados JSON recebidos para objetos Java	
    private static final String API_URL = "https://api.tvmaze.com/search/shows?q=";
    private final OkHttpClient client;
    private final Gson gson;

    //inicializando os objetos 
    public TVmazeAPI() {
    	
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }
    
//retorna uma lista de objetos do tipo serie, passa como entrada (parametro) o nome da serie digitado pelo usuario
    public List<Serie> buscarSeries(String nomeDaSerie) {
        try {
          
        	//pega o nome digitado pelo usuario e prepara para usar na internet, isso para montar a url correta
            String nomeParaBusca = URLEncoder.encode(nomeDaSerie, StandardCharsets.UTF_8.toString());
            
            //junta a url fixa com a serie que quer buscar
            String urlCompleta = API_URL + nomeParaBusca;

            //prepara o pedido para a internet
            Request request = new Request.Builder()
                    .url(urlCompleta)
                    .build();

          //envia o pedido e salva a resposta da internet (response)
            try (Response response = client.newCall(request).execute()) {
            	
            	//verifica se foi bem sucedido, se não foi, devolve o num do erro e retorna a lista vazia.
                if (!response.isSuccessful()) {
                	
                    System.err.println("Ocorreu um Erro na API: " + response.code() + " " + response.message());
                    
                    return new ArrayList<>();
                }

                //le o corpo da mensagem, e se tem conteudo ou veio vazio. Se esta vazio retorna a lista vazia.
                ResponseBody body = response.body();
                
                if (body == null) {
                	
                    System.err.println("A resposta da API está vazia.");
                    return new ArrayList<>();
                    
                }

                //resposta é tranformada em String.java, mantendo o formato
                String json = body.string();
                
                //pega a resposta da api, tranforma em objetos.java e coloca na lista
                Type listType = new TypeToken<ArrayList<ApiResult>>() {}.getType();
                List<ApiResult> apiResults = gson.fromJson(json, listType);

               //verifica se a conversão deu certo, se não deu, retorna uma lista vazia
                if (apiResults == null) {
                	
                    return new ArrayList<>();
                }
                
                return apiResults.stream() 
                		
                        .map(ApiResult::getShow) // pra cada resultado da apiResults ele pega o show
                        .filter(s -> s != null) // se algum show vier nulo ele igonra                      
                        .collect(Collectors.toList()); //coloca tudo em uma lista final com as informações da serie (Show)

            }
            
            //erro de operação de entrada e saida
        } catch (IOException e) {
        	
        	//err é expecifico para mensagens de erro, quando o erro é capturado é armazenado em "e", no final aparece uma mensagem para erro expecifico.
            System.err.println("Erro de conexão ao tentar se comunicar com a API: " + e.getMessage());
            
            return new ArrayList<>(); 
        }
    }

   
     //classe interna pra guardar daos da serie 
    private static class ApiResult {
       
        private Serie show;//contem dados da serie 

        public Serie getShow() {
        	
            return show;
        }
    }
}