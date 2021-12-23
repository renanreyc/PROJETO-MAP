package main;
public class Main {
    public static void main(String[] args) throws Exception {
        Professor professor = new Professor("Claudio");
        Disciplina disciplinaAlgebra = new Disciplina("Algebra", professor);
        Disciplina disciplinaFilosofia = new Disciplina("Filosofia", professor);

        disciplinaAlgebra.cadastrarHorario("Quarta - 10:00");
        disciplinaFilosofia.cadastrarHorario("Segunda - 07:00");

        Aluno carlos = new Aluno("Carlos");
        Aluno renan = new Aluno("Renan");
        Aluno eliseu = new Aluno("Eliseu");

        disciplinaAlgebra.cadastrarAluno(carlos);
        disciplinaAlgebra.cadastrarAluno(renan);
        disciplinaAlgebra.cadastrarAluno(eliseu);

        professor.cadastrarDisciplina(disciplinaAlgebra);
        professor.cadastrarDisciplina(disciplinaFilosofia);

        carlos.cadastrarDisciplina(disciplinaAlgebra);
        carlos.cadastrarDisciplina(disciplinaFilosofia);

        imprimirDisciplinasProfessor(professor);
        System.out.println();
        imprimirHorarioProfessor(professor);
        System.out.println();
        imprimirAlunosDisciplina(disciplinaAlgebra);
        System.out.println();
        imprimirDisciplinasAluno(carlos);
        System.out.println();
        horarioAluno(carlos);
        System.out.println();
        imprimirNumAlunosDisciplina(disciplinaAlgebra);
    }

    static void imprimirDisciplinasProfessor(Professor professor) {
        System.out.println("Disciplinas do professor " + professor.getNome() + ": ");
        for (Disciplina disciplina : professor.getDisciplinas()) {
            System.out.println(disciplina.getNome() + " - " + disciplina.getHorario());
        }
    }

    static void imprimirHorarioProfessor(Professor professor) {
        System.out.println("Horario do professor " + professor.getNome() + ": " + professor.getHorario());
    }

    static void imprimirAlunosDisciplina(Disciplina disciplina) {
        System.out.println("Alunos da disciplina \'" + disciplina.getNome() + "\': ");
        for (Aluno aluno : disciplina.getAlunos()) {
            System.out.println("ID: " + aluno.getId() + " - " + aluno.getNome());
        }
    }

    static void imprimirDisciplinasAluno(Aluno aluno) {
        System.out.println("Disciplinas do aluno " + aluno.getNome() + ": ");
        for (Disciplina disciplina : aluno.getDisciplinas()) {
            System.out.println("ID: " + disciplina.getId() + " - " + disciplina.getNome());
        }
    }

    static void horarioAluno(Aluno aluno) {
        System.out.println("Horario do aluno " + aluno.getNome() + ": " + aluno.getHorario());

    }

    static void imprimirNumAlunosDisciplina(Disciplina disciplina){
        System.out.println("A quantidade de alunos na disciplina \'" + disciplina.getNome()
                + "\' é de: " + disciplina.getQuantidadeDeAlunos());
    }
}

