package br.com.systempus.systempus.domain.role_object;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.systempus.systempus.domain.Curso;
import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
import br.com.systempus.systempus.domain.ProfessorDisciplina;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professor")
@Getter @Setter
public class Professor extends ProfissionalRole {
    
    @ManyToMany
    @JoinTable(name = "professor_curso", joinColumns = @JoinColumn(name = "professor_id"),
    inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursosLecionados;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference(value = "professor_disciplina")
    private List<ProfessorDisciplina> professorDisciplina;

    @ManyToMany
    @JoinTable(
        name = "preferencia_professor_disciplina",
        joinColumns = @JoinColumn(name = "prof_id"),
        inverseJoinColumns = @JoinColumn(name = "disc_id")
    )
    private Set<Disciplina> disciplinasPreferidas;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DisponibilidadeProfessor> disponibilidadeProfessor;

}
