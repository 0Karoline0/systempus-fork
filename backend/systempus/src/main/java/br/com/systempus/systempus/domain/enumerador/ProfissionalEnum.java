package br.com.systempus.systempus.domain.enumerador;

public enum ProfissionalEnum {

    ADM(0, "Administrador"),
    COORDENADOR(1, "Coordenador"),
    PROFESSOR(2, "Professor");

    private final Integer value;
    private final String name;

    ProfissionalEnum(Integer value, String name){
        this.value = value;
        this.name = name;
    }

}
