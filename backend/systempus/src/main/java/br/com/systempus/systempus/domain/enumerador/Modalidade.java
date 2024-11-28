package br.com.systempus.systempus.domain.enumerador;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.systempus.systempus.error.NotFoundException;

public enum Modalidade {

    PRESENCIAL(0, "Presencial"),
    HIBRIDO(1, "Híbrido"),
    EAD(2, "Ensino à Distância - EAD");

    private final Integer value;
    private final String name;

    Modalidade(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    @JsonValue
    public Integer getValue(){
        return value;
    }


    public String getName(){
        return name;
    }

    @JsonCreator
    public static Modalidade toEnum(Integer codigo){
        for( Modalidade modalidade : Modalidade.values()){
            System.out.println("\n\n\n\n" + modalidade);
            if(codigo == modalidade.getValue()){
                System.out.println("Ok!");
                return modalidade;
            }
        }
        throw new NotFoundException("Valor não encontrado da Modalidade "+codigo);
    }

}