package br.com.systempus.systempus.domain.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.systempus.systempus.domain.Permissoes;
import br.com.systempus.systempus.domain.Role;
import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.repository.PermissoesRepository;

public class UserDetailsImpl implements UserDetails {

    private Usuario usuario;
    private PermissoesRepository repository;

    public UserDetailsImpl(Usuario usuario){
        this.usuario = usuario;
    }

    public UserDetailsImpl(Usuario usuario, PermissoesRepository repository) {
        this.usuario = usuario;
        this.repository = repository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (Role role : Objects.requireNonNullElse(usuario.getRoles(), Collections.<Role>emptySet())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

            List<Permissoes> permissoesDoRole = repository.findByRolesContaining(role);
            for (Permissoes p : permissoesDoRole) {
                authorities.add(new SimpleGrantedAuthority(p.getNome()));
            }
        }

        return authorities;
    }


    @Override
    public String getPassword() {
       return usuario.getPassword();
    }

    @Override
    public String getUsername() {
        return usuario.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
    }

    @Override
    public boolean isEnabled() {
        return true;
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }
}
