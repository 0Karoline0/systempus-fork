package br.com.systempus.systempus.domain.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HorarioAulaDTO {

    private Integer id;
    private LocalTime inicioAula;
    private LocalTime fimAula;    
    
}
