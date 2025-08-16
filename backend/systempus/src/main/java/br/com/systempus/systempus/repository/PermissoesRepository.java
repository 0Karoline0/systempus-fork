package br.com.systempus.systempus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systempus.systempus.domain.Permissoes;
import br.com.systempus.systempus.domain.Role;

public interface PermissoesRepository extends JpaRepository<Permissoes, Long> {
    List<Permissoes> findByRolesContaining(Role role);
}
