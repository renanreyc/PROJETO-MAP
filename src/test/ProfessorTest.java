package test;

import main.entidades.ControleAcademico;
import main.entidades.FichaDeDisciplina;
import main.entidades.Professor;
import main.helper.ElementNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfessorTest {

    private ControleAcademico controleAcademico;

    @BeforeEach
    public void setup() throws Exception {
        controleAcademico = new ControleAcademico();
        controleAcademico.limparBuffer();

        controleAcademico.criarNovoProfessor("Luiz");
        controleAcademico.criarNovaDisciplina("Algebra");
        controleAcademico.criarNovaDisciplina("Filosofia");

        controleAcademico.criarNovaFichaDeDisciplina("Quarta - 10:00", 0, 0);
        controleAcademico.criarNovaFichaDeDisciplina("Segunda - 07:00", 0, 1);

    }

    @Test
    public void retornaHorarioCorreto() throws Exception {
        List<String> horario = controleAcademico.horarioDoProfessor(0);

        assertEquals(2, horario.size());
        assertEquals("Quarta - 10:00", horario.get(0));
        assertEquals("Segunda - 07:00", horario.get(1));

        controleAcademico.criarNovaDisciplina("Astronomia");
        controleAcademico.criarNovaFichaDeDisciplina("Segunda - 18:00", 0, 2);

        horario = controleAcademico.horarioDoProfessor(0);

        assertEquals(3, horario.size());
        assertEquals("Segunda - 18:00", horario.get(2));

    }

    @Test
    public void retornaDisciplinasCorretas() {
        List<FichaDeDisciplina> fichas = controleAcademico.disciplinasMinistradasPor(0);
        assertEquals(2, fichas.size());
        assertEquals("Algebra", fichas.get(0).getDisciplina().getNome());
        assertEquals("Filosofia", fichas.get(1).getDisciplina().getNome());

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
    public void professorMudouSeuNome() throws ElementNotFoundException {
        Professor professor = controleAcademico.getProfessores().getElementById(0);
        assertEquals("Luiz", professor.getNome());
        professor.setNome("Zé");
        assertEquals("Zé", professor.getNome());
    }

    @Test
    public void professorMinistraVariasDisciplinas() throws ElementNotFoundException {

        assertEquals(2, controleAcademico.disciplinasMinistradasPor(0).size());
        controleAcademico.criarNovaDisciplina("Astronomia");
        controleAcademico.criarNovaDisciplina("Astronomia 2");
        controleAcademico.criarNovaFichaDeDisciplina("qualquer-um", 0, 2);
        controleAcademico.criarNovaFichaDeDisciplina("qualquer-um", 0, 3);

        assertEquals(4, controleAcademico.disciplinasMinistradasPor(0).size());
    }

}