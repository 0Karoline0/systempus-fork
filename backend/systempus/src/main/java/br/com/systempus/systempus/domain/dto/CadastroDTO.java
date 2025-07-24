package br.com.systempus.systempus.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CadastroDTO {

    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private String token;
    private String foto;
    private String senha;

    public CadastroDTO(Integer id, String nome, String token, String telefone, String email, String foto, String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.token = token;
        this.foto = foto;
        this.senha = senha;
    }

}
