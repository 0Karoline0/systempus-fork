package br.com.systempus.systempus.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.HorarioDisciplina;
import br.com.systempus.systempus.domain.enumerador.DiaSemana;
import br.com.systempus.systempus.domain.enumerador.Turno;

public class HorarioDisciplinaDTO {

	 private DiaSemana diaSemana;
	    private Integer horarioAulaId;
	    private Turno turno;

	    public HorarioDisciplinaDTO(DiaSemana diaSemana, Integer horarioAulaId, Turno turno) {
			super();
			this.diaSemana = diaSemana;
			this.horarioAulaId = horarioAulaId;
			this.turno = turno;
		}

		public static List<HorarioDisciplinaDTO> convertToDTO(List<HorarioDisciplina> horarios) {
	        return horarios.stream().map(h -> new HorarioDisciplinaDTO(
	            h.getDiaSemana(),
	            h.getHorarioAula().getId(),
	            h.getHorarioAula().getPeriodo().getTurno()
	        )).collect(Collectors.toList());
	    }

		public DiaSemana getDiaSemana() {
			return diaSemana;
		}

		public void setDiaSemana(DiaSemana diaSemana) {
			this.diaSemana = diaSemana;
		}

		public Integer getHorarioAulaId() {
			return horarioAulaId;
		}

		public void setHorarioAulaId(Integer horarioAulaId) {
			this.horarioAulaId = horarioAulaId;
		}

		public Turno getTurno() {
			return turno;
		}

		public void setTurno(Turno turno) {
			this.turno = turno;
		}

		
	
}
