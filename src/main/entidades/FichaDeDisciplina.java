package main.entidades;

import main.helper.Identificator;

public class FichaDeDisciplina implements Identificator {

    static int idAtual = 0;

    private int id;
    private String horario;
    private Professor professor;
    private Disciplina disciplina;

    public FichaDeDisciplina(String horario, Professor professor, Disciplina disciplina) {
        this.id = FichaDeDisciplina.idAtual++;
        this.horario = horario;
        this.professor = professor;
        this.disciplina = disciplina;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FichaDeDisciplina that = (FichaDeDisciplina) o;
        return professor.getId() == that.professor.getId();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String getHorario() {
        return horario;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }
}
