package br.com.systempus.systempus.domain;

import java.util.List;

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

    
    @ManyToMany
    @JoinTable(name="professor_disciplina", joinColumns = @JoinColumn(name="professor_id"),
    inverseJoinColumns = @JoinColumn(name="disciplina_id"))
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "professor")
    // @JsonManagedReference(value = "disponibilidade_professor_professor")
    private List<DisponibilidadeProfessor> disponibilidadeProfessor;
    public Professor(){

    }

    public Professor(String cpf, String nome, String telefone){
        this.setCpf(cpf);
        this.setNome(nome);
        this.setTelefone(telefone);
    }

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<DisponibilidadeProfessor> getDisponibilidadeProfessor() {
		return disponibilidadeProfessor;
	}

	public void setDisponibilidadeProfessor(List<DisponibilidadeProfessor> disponibilidadeProfessor) {
		this.disponibilidadeProfessor = disponibilidadeProfessor;
	}
    
    
}