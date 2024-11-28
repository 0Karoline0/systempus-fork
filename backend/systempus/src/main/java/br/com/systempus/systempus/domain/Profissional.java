package br.com.systempus.systempus.domain;

import org.hibernate.validator.constraints.br.CPF;

import br.com.systempus.systempus.domain.enumerador.Status;
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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cpf"), name = "profissional")
public class Profissional {

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

    @Column(name = "status_profissional")
    @NotNull(message = "Campo obrigatório")
    private Status status;

    private String foto;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getCpf(){
        return cpf;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getTelefone(){
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



}
