package br.com.systempus.systempus.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.systempus.systempus.domain.enumerador.Modalidade;
import br.com.systempus.systempus.domain.enumerador.NivelEnsino;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Campo obrigatório")
    private String nome;

    @NotNull(message = "Campo obrigatório")
    @Enumerated(EnumType.ORDINAL)
    private NivelEnsino nivelEnsino;

    @NotNull(message = "Campo obrigatório")
    private Integer qtdPeriodos;

    @NotNull(message = "Campo obrigatório")
    @Enumerated(EnumType.ORDINAL)
    private Modalidade modalidade;

    private Integer cargaTotal;

    // @JsonManagedReference(value = "curso_modulos")
    @OneToMany(mappedBy = "curso")
    private List<Modulo> modulos;

    @JsonBackReference(value = "curso_coordenador")
    @ManyToOne
    @JoinColumn(name = "coordenador_id", nullable = true)
    private Coordenador coordenador;

    @ManyToMany (mappedBy = "cursos")
    private List<Professor> professores;

    @OneToMany(mappedBy = "curso")
    private List<Periodo> periodos;

    @Override
    public String toString() {
        return "Curso [id=" + id + ", nome=" + nome + ", nivelEnsino=" + nivelEnsino + ", qtdPeriodos=" + qtdPeriodos
                + ", modalidade=" + modalidade + ", cargaTotal=" + cargaTotal + ", modulo=" + modulos
                + ", coordenador=" + coordenador + "]";
    }

}
