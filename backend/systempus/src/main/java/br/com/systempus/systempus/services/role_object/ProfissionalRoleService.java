package br.com.systempus.systempus.services.role_object;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.enumerador.ProfissionalEnum;
import br.com.systempus.systempus.domain.role_object.Profissional;
import br.com.systempus.systempus.domain.role_object.ProfissionalRole;
import br.com.systempus.systempus.repository.role_object.ProfissionalRoleRepository;

@Service
public class ProfissionalRoleService {

    @Autowired
    private ProfissionalRoleRepository repository;

    public ProfissionalRole getByProfissionalEnum(ProfissionalEnum tipoProfissional) {
        return repository.getByTipoProfissional(tipoProfissional);
    }

    public ProfissionalRole save(ProfissionalRole profissionalRole) {
        return repository.save(profissionalRole);
    }

    public Optional<Profissional> getByProfissionalId(Integer idProfissional) {
        List<ProfissionalRole> p = repository.getByProfissionalId(idProfissional);
        Optional<Profissional> profissional = null;
        for (ProfissionalRole pr : p) {
            if (pr.getId() == idProfissional) {
                profissional = Optional.ofNullable(pr.getProfissional());
            }
        }
        return profissional;
    }

    public ProfissionalRole update(ProfissionalRole profissionalRole) {
        return repository.saveAndFlush(profissionalRole);
    }

}