package br.com.systempus.systempus.domain.enumerador;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DiaSemana {

    SEGUNDA(0, "Segunda-Feira"),
    TERCA(1, "Terça-Feira"),
    QUARTA(2, "Quarta-Feira"),
    QUINTA(3, "Quinta-Feira"),
    SEXTA(4, "Sexta-Feira"),
    SABADO(5, "Sábado"),
    DOMINGO(6, "Domingo");

    final private int value;
    final private String nome;

    DiaSemana(int value, String nome){
        this.value = value;
        this.nome = nome;
    }

    @JsonValue
    private int getValue(){
        return this.value;
    }

    private String getNome(){
        return this.nome;
    }




}
