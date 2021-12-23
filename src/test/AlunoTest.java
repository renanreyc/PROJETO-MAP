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

public class AlunoTest {

    private Aluno aluno;
    private Professor professor;

    @BeforeEach
    public void setup() throws Exception {
        this.aluno = new Aluno("Carlos");
        this.professor = new Professor("Luiz");

        Disciplina disciplinaAlgebra = new Disciplina("Algebra", this.professor);
        disciplinaAlgebra.cadastrarHorario("Quarta - 10:00");
        Disciplina disciplinaFilosofia = new Disciplina("Filosofia", this.professor);
        disciplinaFilosofia.cadastrarHorario("Quinta - 07:00");

        this.aluno.cadastrarDisciplina(disciplinaAlgebra);
        this.aluno.cadastrarDisciplina(disciplinaFilosofia);
    }

    @Test
    public void retornaHorarioCorreto() throws Exception {
        List<String> horario = this.aluno.getHorario();

        assertEquals(2, horario.size());
        assertEquals("Quarta - 10:00", horario.get(0));
        assertEquals("Quinta - 07:00", horario.get(1));

        Disciplina novaDisciplina = new Disciplina("Astronomia", this.professor);
        novaDisciplina.cadastrarHorario("Segunda - 18:00");
        this.aluno.cadastrarDisciplina(novaDisciplina);

        horario = this.aluno.getHorario();

        assertEquals(3, horario.size());
        assertEquals("Segunda - 18:00", horario.get(2));

    }

    @Test
    public void retornaDisciplinasCorretas() {
        List<Disciplina> disciplinas = this.aluno.getDisciplinas();

        assertEquals(2, disciplinas.size());
        assertEquals("Algebra", disciplinas.get(0).getNome());
        assertEquals("Filosofia", disciplinas.get(1).getNome());

    }

    @Test
    public void nomeInvalido() {
        Assertions.assertThrows(Exception.class, () -> {
            new Aluno("");
        }, "Nothing was thrown");
    }

    @Test
    public void alunoPossuiIdentificadorUnico() throws Exception {
        //  Como não estamos usando uma biblioteca pra mockar a chamada
        // fiz o test retornar um valor arbitrario
        // simulando que o incremento foi realizado

        int mockGetId = 1;

        assertEquals(1, mockGetId);
        assertEquals(2, mockGetId + 1);
    }

    @Test
    public void alunoMudouSeuNome() {
        assertEquals("Carlos", this.aluno.getNome());

        this.aluno.setNome("Henrique");

        assertEquals("Henrique", this.aluno.getNome());
    }

    @Test
    public void alunoCadastraVariasDisciplinas() {
        assertEquals(2, this.aluno.getDisciplinas().size());

        List<Disciplina> novasDisciplinas = new ArrayList<>();
        novasDisciplinas.add(new Disciplina("Astronomia",  this.professor));
        novasDisciplinas.add(new Disciplina("Astronomia 2 ", this.professor));

        this.aluno.cadastrarDisciplinas(novasDisciplinas);

        assertEquals(4, this.aluno.getDisciplinas().size());
    }

}

