package br.com.systempus.systempus.domain.dto;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.Permissoes;
import br.com.systempus.systempus.domain.Role;
import br.com.systempus.systempus.domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class ProfissionalRoleDTO {
    
    List<String> nomesRole;
    
    // public PermissoesDTO(Set<Role> roles, List<Permissoes> permissoes) {
    //     this.roles = roles;
    //     this.permissoes = permissoes.stream().map(p -> new PermissaoDTO(p.getId(), p.getNome())).collect(Collectors.toList());
    //     // this.permissoes = permissoes.stream().map(p -> new PermissaoDTO(roles, permissoes)).collect(Collectors.toList()));
    // }

    public ProfissionalRoleDTO(Usuario usuario) {
        this.nomesRole = usuario.getProfissional().getRoles().stream().map((r) -> r.getTipoProfissional().name()).toList();
    }

}
