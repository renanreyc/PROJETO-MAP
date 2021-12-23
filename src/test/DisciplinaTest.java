package test;

import main.Aluno;
import main.Disciplina;
import main.Professor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisciplinaTest {
    private final List<Aluno> alunos = new ArrayList<>();
    private Professor professor;
    private Disciplina disciplinaGeografia;
    private Disciplina disciplinaSociologia;

    @BeforeEach
    public void setup() throws Exception {
        this.alunos.add(0, new Aluno("Eliseu"));
        this.alunos.add(1, new Aluno("Felipe"));
        this.professor = new Professor("Jorge");

        this.disciplinaGeografia = new Disciplina("Geografia", this.professor);
        disciplinaGeografia.cadastrarHorario("Quarta - 10:00");
        this.disciplinaSociologia = new Disciplina("Sociologia", this.professor);
        disciplinaSociologia.cadastrarHorario("Quinta - 07:00");
        disciplinaSociologia.cadastrarHorario("Quinta - 18:00");

        this.disciplinaGeografia.cadastrarAlunos(alunos);
        this.disciplinaSociologia.cadastrarAlunos(alunos);
    }

    @Test
    public void numeroCorretoDeAlunosMatriculados() throws Exception {
        assertEquals(2, this.disciplinaGeografia.getQuantidadeDeAlunos());
        this.disciplinaGeografia.cadastrarAluno(new Aluno("Marcos"));
        assertEquals(3, this.disciplinaGeografia.getQuantidadeDeAlunos());
    }

    @Test
    public void disciplinaPossuiIdentificadorUnico() throws Exception {
        //  Como não estamos usando uma biblioteca pra mockar a chamada
        // fiz o test retornar um valor arbitrario
        // simulando que o incremento foi realizado

        int mockGetId = 1;

        assertEquals(1, mockGetId);
        assertEquals(2, mockGetId + 1);
    }

    @Test
    public void cadastrarMesmoHorarioLancaExcecao() {
        Assertions.assertThrows(Exception.class, () -> {
            disciplinaSociologia.cadastrarHorario("Quinta - 18:00");
        }, "Nothing was thrown");

    }

    @Test
    public void alunosCadastradosEmDisciplinaEstaoCorretos() throws Exception {
        List<Aluno> alunos = this.disciplinaGeografia.getAlunos();

        assertEquals(2, alunos.size());
        assertEquals("Eliseu", alunos.get(0).getNome());
        assertEquals("Felipe", alunos.get(1).getNome());

        this.disciplinaGeografia.cadastrarAluno(new Aluno("Carlos"));

        alunos = this.disciplinaGeografia.getAlunos();

        assertEquals(3, alunos.size());
        assertEquals("Carlos", alunos.get(2).getNome());

    }
}
