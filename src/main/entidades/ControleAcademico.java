package main.entidades;

import main.helper.ElementNotFoundException;
import main.helper.MyArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControleAcademico {

    private MyArrayList<Aluno> alunos;
    private MyArrayList<Professor> professores;
    private MyArrayList<Disciplina> disciplinas;
    private MyArrayList<FichaDeDisciplina> fichas;

    public ControleAcademico() {
        this.alunos = new MyArrayList<>();
        this.professores = new MyArrayList<>();
        this.disciplinas = new MyArrayList<>();
        this.fichas = new MyArrayList<>();
    }

    public void criarNovoAluno(String nome) {
        if(nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        this.alunos.add(new Aluno(nome));
    }

    public void criarNovoProfessor(String nome) {
        if(nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        this.professores.add(new Professor(nome));
    }

    public void criarNovaDisciplina(String nome) {
        if(nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido.");
        }

        this.disciplinas.add(new Disciplina(nome));
    }

    public void criarNovaFichaDeDisciplina(String horario, int idProfessor, int idDisciplina) throws ElementNotFoundException {
        Professor professor = this.professores.getElementById(idProfessor);
        Disciplina disciplina = this.disciplinas.getElementById(idDisciplina);
        this.fichas.add(new FichaDeDisciplina(horario, professor, disciplina));
    }

    public List<FichaDeDisciplina> disciplinasMinistradasPor(int idProfessor) {
        return this.fichas.stream()
                .filter(fichaDeDisciplina -> fichaDeDisciplina.getProfessor().getId() == idProfessor)
                .collect(Collectors.toList());
    }

    public List<String> horarioDoProfessor(int idProfessor) {
        return this.fichas.stream()
                .filter(fichaDeDisciplina -> fichaDeDisciplina.getProfessor().getId() == idProfessor)
                .map(fichaDeDisciplina -> fichaDeDisciplina.getHorario())
                .collect(Collectors.toList());
    }

    public List<Aluno> alunosMatriculadosEm(int idDisciplina) {
        return this.alunos.stream()
                .filter(aluno -> aluno.getRDM().estaMatriculadoEm(idDisciplina))
                .collect(Collectors.toList());
    }

    public List<FichaDeDisciplina> disciplinasDe(int idAluno) throws ElementNotFoundException {
        Aluno aluno = this.alunos.getElementById(idAluno);
        return aluno.getRDM().getDisciplinas();
    }

    public List<String> horarioDoAluno(int idAluno) throws ElementNotFoundException {
        Aluno aluno = this.alunos.getElementById(idAluno);

        return aluno.getRDM().getDisciplinas().stream()
                .map(fichaDeDisciplina -> fichaDeDisciplina.getHorario())
                .collect(Collectors.toList());
    }

    public int quantidadeDeAlunosDeUmaDisciplina(int idDisciplina) {
        int contador = 0;

        for (Aluno aluno : this.alunos) {
            boolean estaMatriculado = aluno.getRDM().estaMatriculadoEm(idDisciplina);
            contador += estaMatriculado ? 1 : 0;
        }

        return contador;
    }

    public MyArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public MyArrayList<Professor> getProfessores() {
        return professores;
    }
    
    public MyArrayList<FichaDeDisciplina> getFichas() {
        return fichas;
    }

    public void limparBuffer() {
        Aluno.idAtual = 0;
        Professor.idAtual = 0;
        Disciplina.idAtual = 0;
        FichaDeDisciplina.idAtual = 0;
    }

}
