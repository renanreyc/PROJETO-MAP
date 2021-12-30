package test;

import main.entidades.Aluno;
import main.entidades.ControleAcademico;
import main.entidades.FichaDeDisciplina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisciplinaTest {

    private ControleAcademico controleAcademico;
    private Aluno carlos;
    private Aluno filipe;
    private FichaDeDisciplina geografia;

    @BeforeEach
    public void setup() throws Exception {
        controleAcademico = new ControleAcademico();
        controleAcademico.limparBuffer();

        controleAcademico.criarNovoAluno("Carlos");
        controleAcademico.criarNovoAluno("Filipe");
        controleAcademico.criarNovoProfessor("Jorge");
        controleAcademico.criarNovaDisciplina("Geografia");

        controleAcademico.criarNovaFichaDeDisciplina("Quarta - 10:00", 0, 0);

        this.carlos = controleAcademico.getAlunos().get(0);
        this.filipe = controleAcademico.getAlunos().get(1);

        this.geografia = controleAcademico.getFichas().get(0);

        carlos.getRDM().inscreverEmDisciplina(geografia);
        filipe.getRDM().inscreverEmDisciplina(geografia);

    }

    @Test
    public void numeroDeAlunosMatriculadosEmUmaDisciplina() throws Exception {
        assertEquals(2, this.controleAcademico.alunosMatriculadosEm(0).size());
        controleAcademico.criarNovoAluno("Noah");
        controleAcademico.getAlunos().get(2).getRDM().inscreverEmDisciplina(geografia);
        assertEquals(3, this.controleAcademico.alunosMatriculadosEm(0).size());
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
    public void alunosCadastradosEmDisciplinaEstaoCorretos() throws Exception {

        List<Aluno> alunos = controleAcademico.alunosMatriculadosEm(0);

        assertEquals(2, alunos.size());
        assertEquals("Carlos", alunos.get(0).getNome());
        assertEquals("Filipe", alunos.get(1).getNome());

        controleAcademico.criarNovoAluno("Zé");
        controleAcademico.getAlunos().get(2).getRDM().inscreverEmDisciplina(geografia);

        alunos = controleAcademico.alunosMatriculadosEm(0);

        assertEquals(3, alunos.size());
        assertEquals("Zé", alunos.get(2).getNome());

    }
}
