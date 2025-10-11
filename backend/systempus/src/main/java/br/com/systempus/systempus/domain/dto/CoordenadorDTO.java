package br.com.systempus.systempus.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.dto.professor.ProfessorDTO;
import br.com.systempus.systempus.domain.enumerador.Status;
import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import br.com.systempus.systempus.domain.role_object.Coordenador;
import br.com.systempus.systempus.domain.role_object.Professor;
import br.com.systempus.systempus.domain.role_object.Profissional;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordenadorDTO {
    private Integer id;
    private String cpf;
    private String nome;
    private String telefone;
    private String email;
    private Status status;
    private StatusAtivacao statusAtivacao;
    private String foto;
    private List<CursoDTO> cursos;

	public CoordenadorDTO(Integer id, String cpf, String nome, String telefone, String email,
            StatusAtivacao statusAtivacao, String foto, List<CursoDTO> cursos) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.statusAtivacao = statusAtivacao;
        this.foto = foto;
        this.cursos = cursos;
    }

    public static CoordenadorDTO convertToDTO(Coordenador coordenador) {
        return new CoordenadorDTO(
            coordenador.getProfissional().getId(),
            coordenador.getProfissional().getCpf(),
            coordenador.getProfissional().getNome(),
            coordenador.getProfissional().getTelefone(),
            coordenador.getProfissional().getEmail(),
            coordenador.getProfissional().getStatusAtivacao(),
            coordenador.getProfissional().getFoto(),
            CursoDTO.convertToDTO(coordenador.getCursosGerenciados())
        );
    }

    public static List<CoordenadorDTO> convertToDTO(List<Profissional> profissionais) {
        return profissionais.stream().map(
            p -> convertToDTO((Coordenador) p.getByRole(br.com.systempus.systempus.domain.enumerador.ProfissionalEnum.COORDENADOR))
        ).collect(Collectors.toList());
    }

    // public static CoordenadorDTO convertToDTO(Coordenador coordenador) {
    //     return new CoordenadorDTO(
    //         coordenador.getId(),
    //         coordenador.getCpf(),
    //         coordenador.getNome(),
    //         coordenador.getTelefone(),
    //         coordenador.getEmail(),
    //         coordenador.getStatus(),
    //         coordenador.getStatusAtivacao(),
    //         coordenador.getFoto(),
    //         CursoDTO.convertToDTO(coordenador.getCursos())
    //     );
    // }



    // public static List<CoordenadorDTO> convertToDTO(List<Professor> professores) {
    //     return professores.stream()
    //         .map(c -> new CoordenadorDTO(
    //             c.getId(),
    //             c.getProfissional().getNome(),
                
    //             ModuloDTO.convertToDTO(c.getModulos())
    //         ))
    //         .collect(Collectors.toList());
    // }
}
