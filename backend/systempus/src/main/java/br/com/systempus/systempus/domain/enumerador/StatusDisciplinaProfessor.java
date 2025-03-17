package br.com.systempus.systempus.domain.enumerador;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.systempus.systempus.error.NotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusDisciplinaProfessor{

    INTERESSE(0, "Possui Interesse"),
    MINISTROU(1, "Ministrou"),
    AMBOS(2, "Tem interesse e já ministrou");

    private final Integer value;
    private final String name;

    @JsonValue
    public Integer getValue(){
        return value;
    }

    public String getName(){
        return name;
    }

    @JsonCreator
    public static StatusDisciplinaProfessor toEnum(Integer codigo){
        for (StatusDisciplinaProfessor statusDisciplina : StatusDisciplinaProfessor.values()){
            if (codigo == statusDisciplina.getValue()){
                return statusDisciplina;
            }
        }
        throw new NotFoundException("Valor não encontrado nos Status Disciplina Professor: " + codigo);
    }

}
