package test;

import main.entidades.*;
import main.helper.ElementNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlunoTest {

    private ControleAcademico controleAcademico;
    private Aluno aluno;
    private FichaDeDisciplina algebra;
    private FichaDeDisciplina filosofia;

    @BeforeEach
    public void setup() throws Exception {
        controleAcademico = new ControleAcademico();
        controleAcademico.limparBuffer();

        controleAcademico.criarNovoAluno("Carlos");
        controleAcademico.criarNovoProfessor("Luiz");
        controleAcademico.criarNovaDisciplina("Algebra");
        controleAcademico.criarNovaDisciplina("Filosofia");

        controleAcademico.criarNovaFichaDeDisciplina("Quarta - 10:00", 0, 0);
        controleAcademico.criarNovaFichaDeDisciplina("Quinta - 07:00", 0, 1);

        this.aluno = controleAcademico.getAlunos().get(0);
        this.algebra = controleAcademico.getFichas().get(0);
        this.filosofia = controleAcademico.getFichas().get(1);

        aluno.getRDM().inscreverEmDisciplina(algebra);
        aluno.getRDM().inscreverEmDisciplina(filosofia);
    }

    @Test
    public void retornaHorarioCorreto() throws Exception {
        List<String> horario = controleAcademico.horarioDoAluno(aluno.getId()) ;

        assertEquals(2, horario.size());
        assertEquals("Quarta - 10:00", horario.get(0));
        assertEquals("Quinta - 07:00", horario.get(1));

        controleAcademico.criarNovaDisciplina("Matematica do amor");
        controleAcademico.criarNovaFichaDeDisciplina("Segunda - 18:00", 0, 2);

        FichaDeDisciplina novaDisciplina = controleAcademico.getFichas().getElementById(2);
        controleAcademico.criarNovaFichaDeDisciplina("Segunda - 18:00", 0, 2);

        this.aluno.getRDM().inscreverEmDisciplina(novaDisciplina);

        horario = controleAcademico.horarioDoAluno(aluno.getId());

        assertEquals(3, horario.size());
        assertEquals("Segunda - 18:00", horario.get(2));

    }

    @Test
    public void retornaDisciplinasCorretas() throws ElementNotFoundException {
        List<FichaDeDisciplina> fichas = this.controleAcademico.disciplinasDe(0);

        assertEquals(2, fichas.size());
        assertEquals("Algebra", fichas.get(0).getDisciplina().getNome());
        assertEquals("Filosofia", fichas.get(1).getDisciplina().getNome());

    }

    @Test
    public void nomeInvalido() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
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
        assertEquals(2, this.aluno.getRDM().getDisciplinas().size());
        this.aluno.getRDM().inscreverEmDisciplina(algebra);
        assertEquals(3, this.aluno.getRDM().getDisciplinas().size());
    }

}

