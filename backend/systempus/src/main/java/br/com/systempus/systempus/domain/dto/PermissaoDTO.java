package br.com.systempus.systempus.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PermissaoDTO {
    
    private Long id;
    private String nome;

    public PermissaoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
