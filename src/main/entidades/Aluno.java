package main.entidades;

import main.helper.Identificator;

public class Aluno implements Identificator {
    static int idAtual = 0;

    private int id;
    private String nome;
    private RDM rdm;

    public Aluno(String nome) {
        if(nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        this.rdm = new RDM();
        this.id = Aluno.idAtual++;
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

    public RDM getRDM() {
        return this.rdm;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Aluno aluno = (Aluno) object;
        return id == aluno.id;
    }

}
