package main.entidades;

import main.helper.Identificator;

public class Professor implements Identificator{
    static int idAtual = 0;

    private int id;
    private String nome;

    public Professor(String nome) {
        if(nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        this.id = Professor.idAtual++;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
