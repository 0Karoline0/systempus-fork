package br.com.systempus.systempus.domain.embeddableclass;

import jakarta.persistence.Embeddable;

@Embeddable
public class HorarioDisciplinaId {

    private Integer horarioAulaId;
    private Integer disciplinaId;

    public Integer getHorarioAulaId() {
        return horarioAulaId;
    }

    public void setHorarioAulaId(Integer horarioAulaId) {
        this.horarioAulaId = horarioAulaId;
    }

    public Integer getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Integer disciplinaId) {
        this.disciplinaId = disciplinaId;
    }


}
