package br.com.systempus.systempus.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TokenDTO {
    
    private String token;
    private Integer userId;

    public TokenDTO(){}

    public TokenDTO(String token, Integer userId) {
        this.token = token;
        this.userId = userId;
    }
}
