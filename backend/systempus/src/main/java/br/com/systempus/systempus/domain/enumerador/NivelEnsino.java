package br.com.systempus.systempus.domain.enumerador;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.systempus.systempus.error.NotFoundException;

public enum NivelEnsino {

    INFANTIL(0, "Infantil"),
    FUNDAMENTAL(1, "Fundamental"),
    MEDIO(2, "Médio"),
    SUPERIOR(3, "Superior"),
    POSGRADUACAO(4, "Pós-Graduação");

    private final int value;
    private final String nome;

    NivelEnsino(int value, String nome){
        this.value = value;
        this.nome = nome;
    }

    @JsonValue
    public int getValue(){
        return value;
    }

    public String getNome(){
        return nome;
    }

    @JsonCreator
    public static NivelEnsino toEnum(Integer codigo){
        for( NivelEnsino nivelEnsino : NivelEnsino.values()){
            if(codigo == nivelEnsino.getValue()){
                return nivelEnsino;
            }
        }
        throw new NotFoundException("Valor não encontrado da Nível de Ensino " + codigo);
    }
}