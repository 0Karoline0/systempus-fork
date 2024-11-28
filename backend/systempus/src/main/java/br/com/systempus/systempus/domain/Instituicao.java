package br.com.systempus.systempus.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "instituicao")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String cnpj;

    @OneToMany(mappedBy = "instituicao")
    @JsonBackReference(value = "instituicao_periodos")
    private List<Periodo> periodos;

    @OneToMany(mappedBy = "instituicao")
    @JsonBackReference(value = "instituicao_cargas_horarias")
    private List<CargaHoraria> cargasHorarias;

}
