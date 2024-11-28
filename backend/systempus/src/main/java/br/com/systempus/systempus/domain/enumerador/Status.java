package br.com.systempus.systempus.domain.enumerador;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.systempus.systempus.error.NotFoundException;

public enum Status {

    ATIVO(0, "Ativo"),
    DESAIVADO(1, "Desativado"),
    LINCENCA(2, "Licença");

    private final Integer value;
    private final String name;

    Status(Integer value, String name){
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
    public static Status toEnum(Integer codigo){
        for (Status status : Status.values()){
            if (codigo == status.getValue()){
                return status;
            }
        }
        throw new NotFoundException("Valor não encontrado nos Status: " + codigo);
    }

}