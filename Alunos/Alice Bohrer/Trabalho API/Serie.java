package classes;

import java.util.List;

public class Serie {
	
	//atibutos que toda serie deve ter
	private int id;
	private String name;
	private String language;
	private List <String> genres;
	private Rating rating;
    private String status;
    private String premiered;
    private String ended;
    private Network network;
    
    
    //guarda a media de avaliação da serie 
    public static class Rating{
    	
    	private Double average;
    }
     
    //guarda o nome do canal/emissora da serie
    public static class Network {
    	
    	private String name;
    }
    
    //getter para ler o valor dos atributos, pois são privados.
    public String getNome() {return this.name; }
    public int id( ) { return id;}
    public String getIdioma( ) { return language;}
    public List<String> getGeneros() {return genres;}
    public String getEstado() {return status;}
    public String getEstreia( ) { return premiered;}
    
    
    //se a avaliação não for nula, mostra o valor da valiação, se for nula, mostra 0.0
    public Double getNota() {
    	if( rating != null && rating.average!= null ) {
    		
    		return rating.average;
    		
    	}
    	
    	return 0.0;
    	
    }
    
    
    //verifica se a serie tem uma data de fim, se tiver mostra ela, se não tiver mostra que ela ainda esta presente.
    public String getFim() {
    	
    	if(ended != null) {
    		
    		return ended;
    	}
    	
    	return "Presente";
    }
    
    //verifica se tem emissora/ canal, se tem mostra o nome, se não, mostra que não há.
    public String getEmissora() {
    	
    	if( network != null && network.name != null) {
    		
    		return network.name;
    		
    	}
    	
    	return "N/A";
    	
    	
    }
    
    //metodo para apresentar a serie de uma forma mais bonitinha.
    public void display() {
    	
    	 System.out.println("------------------------------------------");
         System.out.println("Nome: " + getNome());
         System.out.println("Idioma: " + getIdioma());
         System.out.println("Gêneros: " + String.join(", ", getGeneros()));
         System.out.println("Nota: " + getNota() + "/10");
         System.out.println("Estado: " + getEstado());
         System.out.println("Estreia: " + getEstreia());
         System.out.println("Fim: " + getFim());
         System.out.println("Emissora: " + getEmissora());
         System.out.println("------------------------------------------");	
  	
    }
   
    }

