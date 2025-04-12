package br.com.systempus.systempus.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProfessorCompatibilidadeDTO {
    private Integer professorId;
    private String nome;
    private String foto;
    private String email;
    private String telefone;
    private Double compatibilidadePercent;
    private String statusHorario;
    private String statusPreferencia;

    // Construtor
    public ProfessorCompatibilidadeDTO(Integer professorId, String nome, String foto, String email,
                                       String telefone, Double compatibilidadePercent,
                                       String statusHorario, String statusPreferencia) {
        this.professorId = professorId;
        this.nome = nome;
        this.foto = foto;
        this.email = email;
        this.telefone = telefone;
        this.compatibilidadePercent = compatibilidadePercent;
        this.statusHorario = statusHorario;
        this.statusPreferencia = statusPreferencia;
    }
}
