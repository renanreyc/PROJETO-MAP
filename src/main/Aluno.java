package main;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    static int numeroDeAlunos = 0;

    private int id;
    private String nome;
    private List<Disciplina> disciplinas;

    public Aluno(String nome) throws Exception{
        if(nome.isEmpty()){
            throw new Exception("Nome inválido.");
        }

        this.id = Aluno.numeroDeAlunos++;
        this.nome = nome;
        this.disciplinas = new ArrayList<>();
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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void cadastrarDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void cadastrarDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas.addAll(disciplinas);
    }

    public List<String> getHorario() {
        List<String> horarios = new ArrayList<String>();

        for(Disciplina disciplina : disciplinas) {
            horarios.add(disciplina.getHorario());
        }

        return horarios;
    }
}
