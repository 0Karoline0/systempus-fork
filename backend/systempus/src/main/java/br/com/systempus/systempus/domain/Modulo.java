package br.com.systempus.systempus.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "modulo")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Campo Obrigatório")
    private String nome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInicio;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataFim;

    @ManyToOne
    @JsonBackReference(value = "curso_modulos")
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // @JsonManagedReference(value = "disciplina_modulo")
    @OneToMany(mappedBy = "modulo")
    private List<Disciplina> disciplinas;

}
