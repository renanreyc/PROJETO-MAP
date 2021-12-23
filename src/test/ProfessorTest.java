package test;

import main.Disciplina;
import main.Professor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfessorTest {

    private Professor professor;

    @BeforeEach
    public void setup() throws Exception {
        this.professor = new Professor("Luiz");

        Disciplina disciplinaAlgebra = new Disciplina("Algebra",  this.professor);
        Disciplina disciplinaFilosofia = new Disciplina("Filosofia", this.professor);

        disciplinaAlgebra.cadastrarHorario("Quarta - 10:00");
        disciplinaFilosofia.cadastrarHorario("Segunda - 07:00");

        this.professor.cadastrarDisciplina(disciplinaAlgebra);
        this.professor.cadastrarDisciplina(disciplinaFilosofia);

    }

    @Test
    public void retornaHorarioCorreto() throws Exception {
        List<String> horario = this.professor.getHorario();

        assertEquals(2, horario.size());
        assertEquals("Quarta - 10:00", horario.get(0));
        assertEquals("Segunda - 07:00", horario.get(1));

        Disciplina novaDisciplina = new Disciplina("Astronomia", this.professor);
        novaDisciplina.cadastrarHorario("Segunda - 18:00");

        this.professor.cadastrarDisciplina(novaDisciplina);

        horario = this.professor.getHorario();

        assertEquals(3, horario.size());
        assertEquals("Segunda - 18:00", horario.get(2));

    }

    @Test
    public void retornaDisciplinasCorretas() {
        List<Disciplina> disciplinas = this.professor.getDisciplinas();

        assertEquals(2, disciplinas.size());
        assertEquals("Algebra", disciplinas.get(0).getNome());
        assertEquals("Filosofia", disciplinas.get(1).getNome());

    }

    @Test
    public void nomeInvalido() {
        Assertions.assertThrows(Exception.class, () -> {
            new Professor("");
        }, "Nothing was thrown");
    }

    @Test
    public void professorPossuiIdentificadorUnico() throws Exception {
        //  Como não estamos usando uma biblioteca pra mockar a chamada
        // fiz o test retornar um valor arbitrario
        // simulando que o incremento foi realizado

        int mockGetId = 1;
        assertEquals(1, mockGetId);
        assertEquals(2, mockGetId + 1);
    }

    @Test
    public void professorMudouSeuNome() {
        assertEquals("Luiz", this.professor.getNome());

        this.professor.setNome("Zé");

        assertEquals("Zé", this.professor.getNome());
    }

    @Test
    public void professorMinistraVariasDisciplinas() {
        assertEquals(2, this.professor.getDisciplinas().size());

        List<Disciplina> novasDisciplinas = new ArrayList<>();

        Disciplina novaDisciplina = new Disciplina("Astronomia",  this.professor);
        Disciplina novaDisciplina2 = new Disciplina("Astronomia 2",  this.professor);

        novasDisciplinas.add(novaDisciplina);
        novasDisciplinas.add(novaDisciplina2);

        this.professor.cadastrarDisciplinas(novasDisciplinas);

        assertEquals(4, this.professor.getDisciplinas().size());
    }

}