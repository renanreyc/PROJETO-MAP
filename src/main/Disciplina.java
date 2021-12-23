package main;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {

    static int numeroDeDisciplinas = 0;

    private int id;
    private String nome;
    private Professor professor;
    private List<Aluno> alunos;
    private List<String> horarios;

    public Disciplina(String nome, Professor professor) {
        this.id = Disciplina.numeroDeDisciplinas++;
        this.nome = nome;
        this.professor = professor;
        this.alunos = new ArrayList<Aluno>();
        this.horarios = new ArrayList<>();
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

    public String getHorario() {
        StringBuilder horarios = new StringBuilder();
        for (int i = 0; i < this.horarios.size(); i++) {
            horarios.append(this.horarios.get(i));
            if (i < this.horarios.size() - 1) {
                horarios.append(" ");
            }
        }
        return horarios.toString();
    }


    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void cadastrarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public int getQuantidadeDeAlunos() {
        return this.alunos.size();
    }

    public void cadastrarAlunos(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void cadastrarHorario(String horario) throws Exception {
        if (this.horarios.contains(horario)) {
            throw new Exception("Horário já cadastrado");
        }

        this.horarios.add(horario);
    }

    public int getQuantidadeDeHorarios() {
        return this.horarios.size();
    }

    public void cadastrarHorarios(List<String> horarios) {
        this.horarios.addAll(horarios);
    }
}
