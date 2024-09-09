package br.com.systempus.systempus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Entity
public class HorarioDocente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "horario_disciplina_horario_aula_id", referencedColumnName = "horario_aula_id"),
        @JoinColumn(name = "horario_disciplina_disciplina_id", referencedColumnName = "disciplina_id")
    })
    private HorarioDisciplina horarioDisciplina;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "disponibilidade_professor_horario_aula_id", referencedColumnName = "horario_aula_id"),
        @JoinColumn(name = "disponibilidade_professor_professor_id", referencedColumnName = "professor_id")
    })
    private DisponibilidadeProfessor disponibilidadeProfessor;

    public HorarioDocente() {
    }

    public HorarioDocente(Integer id, HorarioDisciplina horarioDisciplina,
            DisponibilidadeProfessor disponibilidadeProfessor) {
        this.id = id;
        this.horarioDisciplina = horarioDisciplina;
        this.disponibilidadeProfessor = disponibilidadeProfessor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HorarioDisciplina getHorarioDisciplina() {
        return horarioDisciplina;
    }

    public void setHorarioDisciplina(HorarioDisciplina horarioDisciplina) {
        this.horarioDisciplina = horarioDisciplina;
    }

    public DisponibilidadeProfessor getDisponibilidadeProfessor() {
        return disponibilidadeProfessor;
    }

    public void setDisponibilidadeProfessor(DisponibilidadeProfessor disponibilidadeProfessor) {
        this.disponibilidadeProfessor = disponibilidadeProfessor;
    }

    

}
