package br.com.systempus.systempus.domain;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import jakarta.persistence.DiscriminatorValue;
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
@Getter
@Setter
@DiscriminatorValue("2")
public class Professor extends Profissional{

    @ManyToMany
    @JoinTable(name = "professor_curso", joinColumns = @JoinColumn(name = "professor_id"),
    inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursos;

    @OneToMany(mappedBy = "professor")
    private List<DisponibilidadeProfessor> disponibilidadeProfessor;

    @OneToMany(mappedBy = "professor")
    @JsonBackReference(value = "professor_disciplina")
    private List<ProfessorDisciplina> professorDisciplina;

    @ManyToMany
    @JoinTable(
        name = "preferencia_professor_disciplina",
        joinColumns = @JoinColumn(name = "prof_id"),
        inverseJoinColumns = @JoinColumn(name = "disc_id")
    )
    private Set<Disciplina> disciplinasPreferidas;

    public Professor(){

    }

    public Professor(String nome, String cpf, String email, List<Curso> cursos) {
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setStatusAtivacao(StatusAtivacao.PENDENTE_ATIVACAO);
        setCursos(cursos);
    }
    
}