package br.com.systempus.systempus.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.systempus.systempus.domain.embeddableclass.DisponibilidadeProfessorId;
import br.com.systempus.systempus.domain.enumerador.DiaSemana;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "disponibilidade_professor")
public class DisponibilidadeProfessor {

    public DisponibilidadeProfessor(Professor professor, HorarioAula horarioAula, DiaSemana diaSemana){
        this.professor = professor;
        this.horarioAula = horarioAula;
        this.diaSemana = diaSemana;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonBackReference(value = "disponibilidade_professor_professor")
    private Professor professor;
    
    @ManyToOne
    @JoinColumn(name = "horario_aula_id")
    @JsonBackReference(value = "disponibilidade_professor_horario_aula")
    private HorarioAula horarioAula;

    @OneToMany(mappedBy = "disponibilidadeProfessor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HorarioDocente> horariosDocente;

    private DiaSemana diaSemana;

}