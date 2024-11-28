package br.com.systempus.systempus.domain;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@Table(name = "coordenador")
@DiscriminatorValue("1")
public class Coordenador extends Profissional{

    @OneToMany(mappedBy = "coordenador"/* , cascade = CascadeType.ALL*/)
    // @JsonManagedReference(value = "curso_coordenador")
    private List<Curso> cursos;

    public void setCursos(List<Curso> cursos){
        this.cursos = cursos;
    }

    public List<Curso> getCursos(){
        return cursos;
    }

    @PreRemove
    private void preRemove() {
        for (Curso curso : cursos) {
            curso.setCoordenador(null);
        }
    }
    
    
}