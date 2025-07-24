package br.com.systempus.systempus.domain.enumerador;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import br.com.systempus.systempus.error.NotFoundException;

public enum StatusAtivacao {
    
    ATIVO(0, "Ativo"),
    INATIVO(1, "Inativo"),
    PENDENTE_ATIVACAO(2, "Pendente Ativação");

    private final Integer value;
    private final String status;

    StatusAtivacao(Integer value, String status) {
        this.value = value;
        this.status = status;
    }

    public Integer getValue() {
        return value;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static StatusAtivacao toEnum(Integer value) {
        for (StatusAtivacao status : StatusAtivacao.values()) {
            if (value == status.getValue()) {
                return status;
            }
        }
        throw new NotFoundException("Valor não encontrado no Status de Ativação " + value);
    }

}
