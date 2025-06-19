package model;

public class Pessoa {
private String nome;
private int idade;
private Endereco endereco;

    public Pessoa(String nome, int idade) {
    this.nome = nome;
    this.idade = idade;}
    

    public String getNome() { return nome; }
    public int getIdade() { return idade; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
    }
