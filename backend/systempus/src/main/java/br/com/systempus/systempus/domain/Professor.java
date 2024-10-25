package br.com.systempus.systempus.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.DiscriminatorValue;


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
    private List<DisponibilidadeProfessor> disponibilidadeProfessor;

    public Professor(){

    }

    public Professor(String cpf, String nome, String telefone){
        this.setCpf(cpf);
        this.setNome(nome);
        this.setTelefone(telefone);
    }
}