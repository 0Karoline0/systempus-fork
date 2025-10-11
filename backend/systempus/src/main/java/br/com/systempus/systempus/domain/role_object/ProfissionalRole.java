package br.com.systempus.systempus.domain.role_object;

import java.util.Set;

import br.com.systempus.systempus.domain.enumerador.ProfissionalEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profissional_role")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class ProfissionalRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_profissional")
    private ProfissionalEnum tipoProfissional;

    @ManyToOne
    private Profissional profissional;

    public boolean hasType(ProfissionalEnum profissionalEnum) {
        if (profissionalEnum == ProfissionalEnum.COORDENADOR) {
            return true;
        } else if (profissionalEnum == ProfissionalEnum.PROFESSOR) {
            return true;
        } else if (profissionalEnum == ProfissionalEnum.ADM) {
            return true;
        }
        return false;
    }
}
