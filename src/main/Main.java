package main;

import main.entidades.ControleAcademico;
import main.entidades.FichaDeDisciplina;
import main.entidades.Professor;
import main.entidades.Aluno;
import main.helper.ElementNotFoundException;

import java.util.List;

public class Main {

    static final int FIRST = 0;
    static final int CLAUDIO = 0;
    static final int CARLOS = 0;
    static final int RENAN = 1;
    static final int ELISEU = 2;
    static final int ALGEBRA = 0;
    static final int FILOSOFIA = 1;

    public static void main(String[] args) throws ElementNotFoundException {
        ControleAcademico controleAcademico = new ControleAcademico();

        controleAcademico.criarNovoAluno("Carlos");
        controleAcademico.criarNovoAluno("Renan");
        controleAcademico.criarNovoAluno("Eliseu");
        controleAcademico.criarNovaDisciplina("Algebra");
        controleAcademico.criarNovaDisciplina("Filosofia");
        controleAcademico.criarNovoProfessor("Claudio");
        controleAcademico.criarNovaFichaDeDisciplina("Quarta - 10:00", CLAUDIO, ALGEBRA);
        controleAcademico.criarNovaFichaDeDisciplina("Segunda - 07:00", CLAUDIO, FILOSOFIA);

        Professor professor = controleAcademico.getProfessores().get(CLAUDIO);
        FichaDeDisciplina algebra = controleAcademico.getFichas().get(ALGEBRA);
        FichaDeDisciplina filosofia = controleAcademico.getFichas().get(FILOSOFIA);

        Aluno carlos = controleAcademico.getAlunos().get(CARLOS);
        Aluno eliseu = controleAcademico.getAlunos().get(ELISEU);
        Aluno renan = controleAcademico.getAlunos().get(RENAN);

        carlos.getRDM().inscreverEmDisciplina(algebra);
        carlos.getRDM().inscreverEmDisciplina(filosofia);

        eliseu.getRDM().inscreverEmDisciplina(algebra);
        eliseu.getRDM().inscreverEmDisciplina(filosofia);

        renan.getRDM().inscreverEmDisciplina(algebra);
        renan.getRDM().inscreverEmDisciplina(filosofia);

        List<FichaDeDisciplina> disciplinasDoProfessorClaudio = controleAcademico.disciplinasMinistradasPor(CLAUDIO);
        List<String> horarioDoProfessorClaudio = controleAcademico.horarioDoProfessor(CLAUDIO);
        List<Aluno> alunosDeAlgebra = controleAcademico.alunosMatriculadosEm(ALGEBRA);

        imprimirDisciplinasDeUmProfessor(disciplinasDoProfessorClaudio);
        imprimirHorarioDeUmProfessor(professor.getNome(), horarioDoProfessorClaudio);
        imprimirAlunosDeUmaDisciplina(algebra.getDisciplina().getNome(), alunosDeAlgebra);
        imprimirDisciplinasDeUmAluno(carlos.getNome(), carlos.getRDM().getDisciplinas());
        imprimirHorarioDeUmAluno(carlos.getNome(), controleAcademico.horarioDoAluno(CARLOS));
        imprimirNumeroDeAlunosDeUmDisciplina(algebra.getDisciplina().getNome(), controleAcademico.quantidadeDeAlunosDeUmaDisciplina(ALGEBRA));
    }

    static void imprimirDisciplinasDeUmProfessor(List<FichaDeDisciplina> fichaDeDisciplinas) {
        String nome = fichaDeDisciplinas.get(FIRST).getProfessor().getNome();

        System.out.println("Disciplinas do professor " + nome + ": ");
        for (FichaDeDisciplina ficha : fichaDeDisciplinas) {
            System.out.println(ficha.getDisciplina().getNome() + " - " + ficha.getHorario());
        }

        System.out.println();
    }

    static void imprimirHorarioDeUmProfessor(String nomeDoProfessor, List<String> horarios) {
        System.out.println("Horario do professor " + nomeDoProfessor + ": ");

        for (String horario : horarios) {
            System.out.println(horario);
        }

        System.out.println();
    }

    static void imprimirAlunosDeUmaDisciplina(String nomeDisciplina, List<Aluno> alunos) {
        System.out.println("Alunos da disciplina \'" + nomeDisciplina + "\': ");
        for (Aluno aluno : alunos) {
            System.out.println("ID: " + aluno.getId() + " - " + aluno.getNome());
        }
        System.out.println();
    }

    static void imprimirDisciplinasDeUmAluno(String nomeAluno, List<FichaDeDisciplina> fichas) {
        System.out.println("Disciplinas do aluno " + nomeAluno + ": ");
        for (FichaDeDisciplina ficha : fichas) {
            System.out.println("ID: " + ficha.getDisciplina().getId() + " - " + ficha.getDisciplina().getNome());
        }

        System.out.println();
    }

    static void imprimirHorarioDeUmAluno(String nomeAluno, List<String> horarios) {
        System.out.println("Horario do aluno " + nomeAluno + ": ");

        for (String horario : horarios) {
            System.out.println(horario);
        }

        System.out.println();

    }

    static void imprimirNumeroDeAlunosDeUmDisciplina(String nomeDisciplina, int quantidadeDeDisciplinas){
        System.out.println("A quantidade de alunos na disciplina \'" + nomeDisciplina
                + "\' é de: " + quantidadeDeDisciplinas);
    }

}
