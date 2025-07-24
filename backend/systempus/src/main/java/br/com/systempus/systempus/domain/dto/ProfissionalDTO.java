package br.com.systempus.systempus.domain.dto;

import java.util.List;

import br.com.systempus.systempus.domain.Coordenador;
import br.com.systempus.systempus.domain.Professor;
import br.com.systempus.systempus.domain.enumerador.Status;
import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfissionalDTO {
    private Integer id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private Status status;
    private StatusAtivacao statusAtivacao;
    private String foto;
    private List<CursoDTO> cursos;

	public ProfissionalDTO(Integer id, String cpf, String nome, String telefone, String email, Status status,
            StatusAtivacao statusAtivacao, String foto, List<CursoDTO> cursos) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.status = status;
        this.statusAtivacao = statusAtivacao;
        this.foto = foto;
        this.cursos = cursos;
    }

    public static ProfissionalDTO convertToDTO(Professor professor) {
        return new ProfissionalDTO(
            professor.getId(),
            professor.getCpf(),
            professor.getNome(),
            professor.getTelefone(),
            professor.getEmail(),
            professor.getStatus(),
            professor.getStatusAtivacao(),
            professor.getFoto(),
            CursoDTO.convertToDTO(professor.getCursos())
        );
    }

    public static ProfissionalDTO convertToDTO(Coordenador coordenador) {
        return new ProfissionalDTO(
            coordenador.getId(),
            coordenador.getCpf(),
            coordenador.getNome(),
            coordenador.getTelefone(),
            coordenador.getEmail(),
            coordenador.getStatus(),
            coordenador.getStatusAtivacao(),
            coordenador.getFoto(),
            CursoDTO.convertToDTO(coordenador.getCursos())
        );
    }

    // public static List<ProfissionalDTO> convertToDTO(List<Professor> professores) {
    //     return professores.stream()
    //         .map(c -> new ProfissionalDTO(
    //             c.getId(),
    //             c.getNome(),
                
    //             ModuloDTO.convertToDTO(c.getModulos())
    //         ))
    //         .collect(Collectors.toList());
    // }
}
