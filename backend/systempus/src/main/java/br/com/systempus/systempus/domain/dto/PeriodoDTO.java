package br.com.systempus.systempus.domain.dto;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.Periodo;
import br.com.systempus.systempus.domain.enumerador.Turno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PeriodoDTO {

    private Integer id;
    private Turno turno;
    private LocalTime inicioIntervalo;
    private LocalTime fimIntervalo;
    private LocalTime inicioHorario;
    private LocalTime fimHorario;
    private List<HorarioAulaDTO> horariosAulaDTO;

	public static PeriodoDTO convertToDTO(Periodo periodo) {
        List<HorarioAulaDTO> horariosAulaDTO = periodo.getHorariosAula().stream()
                .map(horarioAula -> new HorarioAulaDTO(horarioAula.getId(), horarioAula.getInicioAula(), horarioAula.getFimAula()))
                .collect(Collectors.toList());

        return new PeriodoDTO(
            periodo.getId(),
            periodo.getTurno(),
            periodo.getInicioIntervalo(),
            periodo.getFimIntervalo(),
            periodo.getInicioHorario(),
            periodo.getFimHorario(),
            horariosAulaDTO
        );
    }	
	
}
