package br.com.systempus.systempus.domain.embeddableclass;

import jakarta.persistence.Embeddable;

@Embeddable
public class HorarioAulaId {

    private Integer cargaHorariaId;

    private Integer periodoId;

    public HorarioAulaId(Integer cargaHorariaId, Integer periodoId) {
        this.cargaHorariaId = cargaHorariaId;
        this.periodoId = periodoId;
    }

    public Integer getCargaHorariaId() {
        return cargaHorariaId;
    }

    public void setCargaHorariaId(Integer cargaHorariaId) {
        this.cargaHorariaId = cargaHorariaId;
    }

    public Integer getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Integer periodoId) {
        this.periodoId = periodoId;
    }




}
