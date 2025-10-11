package br.com.systempus.systempus.domain.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.Modulo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuloDTO {
    private Integer id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<DisciplinaDTO> disciplinas;

    public ModuloDTO(Integer id, String nome, LocalDate dataInicio, LocalDate dataFim, List<DisciplinaDTO> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.disciplinas = disciplinas;
    }

    public static ModuloDTO convertToDTO(Modulo modulo) {
        return new ModuloDTO(
            modulo.getId(),
            modulo.getNome(),
            modulo.getDataInicio(),
            modulo.getDataFim(),
            DisciplinaDTO.convertToDTO(modulo.getDisciplinas())
        );
    }

    public static List<ModuloDTO> convertToDTO(List<Modulo> modulos) {
        return modulos.stream()
            .map(m -> new ModuloDTO(
                m.getId(),
                m.getNome(),
                m.getDataInicio(),
                m.getDataFim(),
                DisciplinaDTO.convertToDTO(m.getDisciplinas())
            ))
            .collect(Collectors.toList());
    }

    
}
