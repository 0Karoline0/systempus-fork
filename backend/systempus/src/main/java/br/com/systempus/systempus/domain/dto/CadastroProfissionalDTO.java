package br.com.systempus.systempus.domain.dto;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastroProfissionalDTO {

    private Integer id;
    private String nome;
    private String email;

    @NotNull(message = "Campo obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    private List<Integer> cursos;

    public CadastroProfissionalDTO(Integer id, String nome, String email, String cpf, List<Integer> cursos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cursos = cursos;
        this.cpf = cpf;
    }

	// public static List<CursoDTO> convertToDTO(List<Curso> cursos) {
    //     return cursos.stream()
    //         .map(c -> new CursoDTO(
    //             c.getId(),
    //             c.getNome(),
    //             c.getNivelEnsino(),
    //             c.getQtdPeriodos(),
    //             c.getModalidade(),
    //             c.getCargaTotal(),
    //             ModuloDTO.convertToDTO(c.getModulos())
    //         ))
    //         .collect(Collectors.toList());
    // }
}
