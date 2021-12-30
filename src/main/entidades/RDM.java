package main.entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RDM {

    private List<FichaDeDisciplina> disciplinas;

    public RDM() {
        this.disciplinas = new ArrayList<>();
    }

    public List<FichaDeDisciplina> getDisciplinas() {
        return disciplinas;
    }

    public void inscreverEmDisciplina(FichaDeDisciplina fichaDeDisciplina) {
        this.disciplinas.add(fichaDeDisciplina);
    }

    public boolean estaMatriculadoEm(int idDisciplina) {
        List<FichaDeDisciplina> fichasFiltradas = this.disciplinas.stream()
                .filter(fichaDeDisciplina -> fichaDeDisciplina.getId() == idDisciplina)
                .collect(Collectors.toList());

        return fichasFiltradas.size() >= 1;
    }

}
