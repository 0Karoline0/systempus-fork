package br.com.systempus.systempus.domain;

import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;
    private String password;
    
    // TODO: Alterar email para estar ligado ao profissional (professor ou coordenador)
    // TODO: Usar proxy usando Apache Engine
    @OneToOne
    @JoinColumn(name="profissional_id")
    private Profissional profissional;
    
    private String getEmail(){
        return Optional.ofNullable(profissional).map(Profissional::getEmail).orElse(null);     
    }


}
