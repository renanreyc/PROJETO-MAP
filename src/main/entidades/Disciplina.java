package main.entidades;

import main.helper.Identificator;

public class Disciplina implements Identificator {
    static int idAtual = 0;

    private int id;
    private String nome;

    public Disciplina(String nome) {
        this.id = Disciplina.idAtual++;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
