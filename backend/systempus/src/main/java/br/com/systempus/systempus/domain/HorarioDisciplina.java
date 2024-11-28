package br.com.systempus.systempus.domain;

import java.util.List;

import br.com.systempus.systempus.domain.embeddableclass.HorarioDisciplinaId;
import br.com.systempus.systempus.domain.enumerador.DiaSemana;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private DiaSemana diaSemana;

    @ManyToOne
    @JoinColumn(name = "horario_aula_id")
    private HorarioAula horarioAula;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @OneToMany(mappedBy = "horarioDisciplina")
    private List<HorarioDocente> horariosDocente;
    
	public HorarioDisciplina(Integer id, DiaSemana diaSemana, HorarioAula horarioAula,
			Disciplina disciplina) {
		this.id = id;
		this.diaSemana = diaSemana;
		this.horarioAula = horarioAula;
		this.disciplina = disciplina;
	}
	
	public HorarioDisciplina(DiaSemana diaSemana, HorarioAula horarioAula,
			Disciplina disciplina) {
		this.diaSemana = diaSemana;
		this.horarioAula = horarioAula;
		this.disciplina = disciplina;
	}


}
