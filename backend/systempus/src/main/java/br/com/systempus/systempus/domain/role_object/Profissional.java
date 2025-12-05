package br.com.systempus.systempus.domain.role_object;

import java.util.HashSet;
import java.util.Set;

import br.com.systempus.systempus.domain.enumerador.ProfissionalEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profissional")
@DiscriminatorValue("1")
@Getter @Setter
public class Profissional extends Pessoa {

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "profissional")
    private Set<ProfissionalRole> roles = new HashSet<>();

    public void addRole(ProfissionalRole role) {
        roles.add(role);
    }

    public ProfissionalRole getByRole(ProfissionalEnum tipoProfissional) {
        for (ProfissionalRole role : roles) {
            if (role.getTipoProfissional() == tipoProfissional) {
                return role;
            }
        }
        return null;
    }

    public boolean hasRoleOf(ProfissionalEnum profissionalEnum) {
        if (profissionalEnum == ProfissionalEnum.PROFESSOR) {
            return isProfessor();
        } else if (profissionalEnum == ProfissionalEnum.COORDENADOR) {
            return isCoordenador();
        }
        return false;
    }

    private boolean isProfessor() {
        for (ProfissionalRole p : roles) {
            if (p instanceof Professor) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isCoordenador() {
        for (ProfissionalRole p : roles) {
            if (p instanceof Coordenador) {
                return true;
            }
        }
        return false;
    }

    

}
