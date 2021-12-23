package main;

import java.util.ArrayList;
import java.util.List;

//A. Quais disciplinas um professor está ministrando;
//B. Metodo de get dos horarios das disciplinas dele

public class Professor {

    static int numeroDeProfessores = 0;

    private final int id;
    private String nome;
    private final List<Disciplina> disciplinas;

    public Professor(String nome) throws Exception {

        if(nome.isEmpty()) {
            throw new Exception("Nome inválido.");
        }

        this.id = Professor.numeroDeProfessores++;
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
        List<String> horarios = new ArrayList<>();

        for(Disciplina disciplina : disciplinas) {
            horarios.add(disciplina.getHorario());
        }

        return horarios;
    }
}
