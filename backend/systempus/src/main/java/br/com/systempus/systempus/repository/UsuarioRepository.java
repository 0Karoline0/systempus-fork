package br.com.systempus.systempus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.systempus.systempus.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUserName(String userName);
    Optional<Usuario> findByProfissionalEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.profissional.id = :idProfissional")
    Optional<Usuario> buscarPorIdDoProfissional(@Param("idProfissional") Integer idProfissional);
    
}
