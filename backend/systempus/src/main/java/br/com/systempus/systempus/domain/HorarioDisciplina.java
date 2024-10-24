package br.com.systempus.systempus.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.systempus.systempus.domain.embeddableclass.HorarioDisciplinaId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "horario_disciplina")
public class HorarioDisciplina {
    
    @EmbeddedId
    private HorarioDisciplinaId id;
    
    @ManyToOne
    @MapsId("horarioAulaId") 
    @JoinColumn(name = "horario_aula_id")
    private HorarioAula horarioAula;

    @ManyToOne
    @MapsId("disciplinaId")
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @OneToMany(mappedBy = "horarioDisciplina")
    private List<HorarioDocente> horariosDocente;
    
    
}
