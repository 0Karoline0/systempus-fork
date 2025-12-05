package br.com.systempus.systempus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.Usuario;
import br.com.systempus.systempus.domain.role_object.Profissional;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.UsuarioRepository;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario getUserByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public void register(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }

    public Usuario findUserById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Usuário com o ID: " + id.toString() + " não existe"));
    }

    public Usuario findUserByEmail(String email) {
        return repository.findByProfissionalEmail(email).orElseThrow(() ->new NotFoundException("Usuário com o email: " + email + " não foi encontrado"));
    }

    public void resetPassword(Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repository.saveAndFlush(usuario);  
    }

    public Usuario getByIdProfissional(Integer idProfissional) {
        return repository.buscarPorIdDoProfissional(idProfissional).orElseThrow(() -> new NotFoundException(Profissional.class.getName(), idProfissional));
    }

    public List<String> getRoles(Integer userId) {
        Usuario usuario = repository.findById(userId).orElseThrow(() -> new NotFoundException(Usuario.class.getName(), userId));
        // return new ProfissionalRoleDTO(usuario);
        return usuario.getProfissional().getRoles().stream().map((r) -> r.getTipoProfissional().name()).toList();
    }

    // public PermissoesDTO getPermissoes(Integer userId) {
    //     Usuario usuario = repository.findById(userId)
    //             .orElseThrow(() -> new NotFoundException(Usuario.class.getName(), userId));

    //     Set<Role> userRoles = usuario.getRoles();
    //     List<Permissoes> p = userRoles.stream().flatMap(role -> permissoesRepository.findByRolesContaining(role).stream()).distinct().toList();
    //     PermissoesDTO p2 = new PermissoesDTO(
    //         userRoles,
    //         p
    //     );

    //     return p2;
    // }

    public Profissional getProfissionalByUserId(Integer userId) {
        Usuario u = findUserById(userId);
        return u.getProfissional();
    }

    // public List<DisciplinaDTO> getDisciplinasPreferidas(Integer id) {
    //     Profissional p = findUserById(id).getProfissional();
    //     // return 
    // }

    
}
