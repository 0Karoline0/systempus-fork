package br.com.systempus.systempus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systempus.systempus.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUserName(String userName);
    Optional<Usuario> findByProfissionalEmail(String email);
    
}
