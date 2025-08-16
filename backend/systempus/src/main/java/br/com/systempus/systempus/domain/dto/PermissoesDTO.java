package br.com.systempus.systempus.domain.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.systempus.systempus.domain.Permissoes;
import br.com.systempus.systempus.domain.Role;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class PermissoesDTO {
    
    Set<Role> roles;
    List<PermissaoDTO> permissoes;
    
    public PermissoesDTO(Set<Role> roles, List<Permissoes> permissoes) {
        this.roles = roles;
        this.permissoes = permissoes.stream().map(p -> new PermissaoDTO(p.getId(), p.getNome())).collect(Collectors.toList());
        // this.permissoes = permissoes.stream().map(p -> new PermissaoDTO(roles, permissoes)).collect(Collectors.toList()));
    }

}
