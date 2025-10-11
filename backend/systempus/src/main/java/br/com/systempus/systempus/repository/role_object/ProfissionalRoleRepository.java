package br.com.systempus.systempus.repository.role_object;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.systempus.systempus.domain.enumerador.ProfissionalEnum;
import br.com.systempus.systempus.domain.role_object.Profissional;
import br.com.systempus.systempus.domain.role_object.ProfissionalRole;

public interface ProfissionalRoleRepository extends JpaRepository<ProfissionalRole, Integer> {
    ProfissionalRole getByTipoProfissional(ProfissionalEnum tipoProfissional);
    List<ProfissionalRole> getByProfissionalId(Integer profissionalId);
}
