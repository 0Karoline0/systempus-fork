package br.com.systempus.systempus.domain.enumerador;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.systempus.systempus.error.NotFoundException;

public enum Turno {

    MATUTINO(0, "Matutino"),
    VESPERTINO(1, "Vespertino"),
    NOTURNO(2, "Noturno"),
    INTEGRAL(3, "Integral");

    private final Integer value;
    private final String nome;

    Turno(Integer value, String nome){
        this.value = value;
        this.nome = nome;
    }

    @JsonValue
    public Integer getValue(){
        return value;
    }

    public String getTurno(){
        return nome;
    }

    @JsonCreator
    public static Turno toEnum(Integer codigo){
        for (Turno turno : Turno.values()){
            if (codigo == turno.getValue()){
                return turno;
            }
        }
        throw new NotFoundException("Valor não encontrado no Turno: " + codigo);
    }

}
