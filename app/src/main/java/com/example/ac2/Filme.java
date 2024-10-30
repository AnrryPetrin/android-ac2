package com.example.ac2;

public class Filme {
    private String titulo;
    private int ano;
    private String sinopse;
    private String diretor;
    private String imagem; // Nome da imagem no recurso mipmap

    public Filme() {
        // Construtor vazio necessário para o Firestore
    }

    public Filme(String titulo, int ano, String sinopse, String diretor, String imagem) {
        this.titulo = titulo;
        this.ano = ano;
        this.sinopse = sinopse;
        this.diretor = diretor;
        this.imagem = imagem;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getDiretor() {
        return diretor;
    }

    public String getImagem() {
        return imagem;
    }
}
