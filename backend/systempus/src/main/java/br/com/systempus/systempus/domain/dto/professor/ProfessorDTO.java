package br.com.systempus.systempus.domain.dto.professor;

import java.util.List;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.dto.CursoDTO;
import br.com.systempus.systempus.domain.dto.DisciplinaDTO;
import br.com.systempus.systempus.domain.enumerador.ProfissionalEnum;
import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import br.com.systempus.systempus.domain.role_object.Professor;
import br.com.systempus.systempus.domain.role_object.Profissional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProfessorDTO {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String foto;
    private StatusAtivacao statusAtivacao;
    private List<CursoDTO> cursosLecionados;
    private List<DisciplinaDTO> disciplinasPreferidas;

    public static ProfessorDTO convertToDTO(Profissional profissional) {
        Professor professorRole = (Professor) profissional.getByRole(ProfissionalEnum.PROFESSOR);
        return new ProfessorDTO(
            profissional.getId(),
            profissional.getNome(),
            profissional.getCpf(),
            profissional.getTelefone(),
            profissional.getEmail(),
            profissional.getFoto(),
            profissional.getStatusAtivacao(),
            CursoDTO.convertToDTO(professorRole.getCursosLecionados()),
            DisciplinaDTO.convertToDTO(professorRole.getDisciplinasPreferidas().stream().collect(Collectors.toList()))
        );
    }

    public static List<ProfessorDTO> convertList(List<Profissional> profissionais) {
        return profissionais.stream().map(
            p -> convertToDTO(p)
        ).collect(Collectors.toList());
    }

}