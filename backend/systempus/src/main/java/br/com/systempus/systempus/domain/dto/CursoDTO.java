package br.com.systempus.systempus.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.Curso;
import br.com.systempus.systempus.domain.enumerador.Modalidade;
import br.com.systempus.systempus.domain.enumerador.NivelEnsino;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoDTO {
    private Integer id;
    private String nome;
    private NivelEnsino nivelEnsino;
    private Integer qtdPeriodos;
    private Modalidade modalidade;
    private Integer cargaTotal;
    private List<ModuloDTO> modulos;

    public CursoDTO(Integer id, String nome, NivelEnsino nivelEnsino, Integer qtdPeriodos, 
                    Modalidade modalidade, Integer cargaTotal, List<ModuloDTO> modulos) {
        this.id = id;
        this.nome = nome;
        this.nivelEnsino = nivelEnsino;
        this.qtdPeriodos = qtdPeriodos;
        this.modalidade = modalidade;
        this.cargaTotal = cargaTotal;
        this.modulos = modulos;
    }

	public static CursoDTO convertToDTO(Curso curso) {
        return new CursoDTO(
            curso.getId(),
            curso.getNome(),
            curso.getNivelEnsino(),
            curso.getQtdPeriodos(),
            curso.getModalidade(),
            curso.getCargaTotal(),
            ModuloDTO.convertToDTO(curso.getModulos())
        );
    }

	public static List<CursoDTO> convertToDTO(List<Curso> cursos) {
        return cursos.stream()
            .map(c -> new CursoDTO(
                c.getId(),
                c.getNome(),
                c.getNivelEnsino(),
                c.getQtdPeriodos(),
                c.getModalidade(),
                c.getCargaTotal(),
                ModuloDTO.convertToDTO(c.getModulos())
            ))
            .collect(Collectors.toList());
    }

}
