package br.com.systempus.systempus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class HorarioDocente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@ManyToOne
    //@JoinColumns({
    //    @JoinColumn(name = "horario_disciplina_horario_aula_id", referencedColumnName = "horario_aula_id"),
    //    @JoinColumn(name = "horario_disciplina_disciplina_id", referencedColumnName = "disciplina_id")
    //})
    //private HorarioDisciplina horarioDisciplina;
    
    @ManyToOne
    @JoinColumn(name = "horario_disciplina_id", referencedColumnName = "id")
    private HorarioDisciplina horarioDisciplina;

    @ManyToOne
    @JoinColumn(name = "disponibilidade_professor_id", referencedColumnName = "id")
    private DisponibilidadeProfessor disponibilidadeProfessor;
    

}
