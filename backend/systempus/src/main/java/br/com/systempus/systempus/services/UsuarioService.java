package br.com.systempus.systempus.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.Profissional;
import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.domain.security.UserDetailsImpl;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.UsuarioRepository;


@Service
public class UsuarioService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private CoordenadorService coordenadorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = repository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new UserDetailsImpl(usuario);
    }

    public void register(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }

    public Optional<Usuario> findUserByEmail(String email) {
        return repository.findByProfissionalEmail(email);
    }

    public void resetPassword(Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repository.saveAndFlush(usuario);  
    }

    public Usuario getByIdProfissional(Integer idProfissional) {
        return repository.buscarPorIdDoProfissional(idProfissional).orElseThrow(() -> new NotFoundException(Profissional.class.getName(), idProfissional));
    }

    
}
