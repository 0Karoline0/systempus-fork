package br.com.systempus.systempus.domain;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "disciplina")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Campo Obrigatório")
    private String nome;

    @NotNull(message = "Campo obrigatório")
    private Integer quantidadeCargas;

    @JsonBackReference(value = "disciplina_modulo")
    @ManyToOne
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    @OneToMany(mappedBy = "disciplina")
    @JsonBackReference(value = "horario_disciplina")
    private List<HorarioDisciplina> horarioDisciplina;

	@OneToMany(mappedBy = "disciplina")
	@JsonBackReference(value = "professor_disciplina")
	private List<ProfessorDisciplina> professorDisciplina;

    @ManyToMany(mappedBy = "disciplinasPreferidas")
    private Set<Professor> professoresPreferem;

}
