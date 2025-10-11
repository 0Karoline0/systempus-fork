package br.com.systempus.systempus.domain.role_object;

import org.hibernate.validator.constraints.br.CPF;

import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cpf"), name = "pessoa")
@Getter
@Setter
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Campo obrigatório")
    @CPF(message = "CPF inválido")
    @Column(unique = true)
    private String cpf;

    @NotNull(message = "Campo Obrigatório")
    @Size(min=4,max=50,message="Descrição deve ter no máximo {max}")
    private String nome;

    private String telefone;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email inválido")
    private String email;

    private String foto;

    @NotNull(message = "Campo obrigatório")
    @Column(name = "status_ativacao")
    private StatusAtivacao statusAtivacao;

}
