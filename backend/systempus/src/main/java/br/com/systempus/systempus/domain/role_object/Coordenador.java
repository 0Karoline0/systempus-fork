package br.com.systempus.systempus.domain.role_object;

import java.util.List;

import br.com.systempus.systempus.domain.Curso;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("2")
public class Coordenador extends ProfissionalRole {
    
    @OneToMany(mappedBy = "coordenador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Curso> cursosGerenciados;

}
