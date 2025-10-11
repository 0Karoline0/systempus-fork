package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.systempus.systempus.domain.dto.CoordenadorDTO;
import br.com.systempus.systempus.domain.enumerador.ProfissionalEnum;
import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import br.com.systempus.systempus.domain.role_object.Coordenador;
import br.com.systempus.systempus.domain.role_object.Professor;
import br.com.systempus.systempus.domain.role_object.Profissional;
import br.com.systempus.systempus.domain.role_object.ProfissionalRole;
import br.com.systempus.systempus.error.DataIntegrityViolationException;
import br.com.systempus.systempus.error.IllegalStateException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.CoordenadorRepository;
import br.com.systempus.systempus.services.role_object.ProfissionalService;

@Service
public class CoordenadorService {

    @Autowired
    private CoordenadorRepository repository;

    @Autowired
    private ProfissionalService profissionalService;

	public Coordenador getOne(Integer id) {
        Profissional p = profissionalService.getProfissionalById(id);
        Coordenador coordenador = (Coordenador) p.getByRole(ProfissionalEnum.COORDENADOR);
        return coordenador;
    }

    public List<CoordenadorDTO> getAll() {
        List<Profissional> p = profissionalService.getAllByTipoProfissional(ProfissionalEnum.COORDENADOR);
        return CoordenadorDTO.convertToDTO(p);
    }

    public void save(Coordenador coordenador) {
        if ((coordenador.getId() == null)) {
            profissionalService.existsByCpf(coordenador.getProfissional().getCpf());
            profissionalService.existsByEmail(coordenador.getProfissional().getEmail());
            repository.save(coordenador);
        } else {
            throw new IllegalStateException(Coordenador.class.getSimpleName().toString());
        }
    }

    public void delete(Integer id) {
        Profissional p = profissionalService.getProfissionalById(id);
        Coordenador coord = (Coordenador) p.getByRole(ProfissionalEnum.COORDENADOR);
        for (ProfissionalRole papel : p.getRoles()) {
            if (papel.getTipoProfissional() == ProfissionalEnum.COORDENADOR) {
                p.getRoles().remove(papel);
            }
        }
        repository.deleteById(coord.getId());
        profissionalService.update(p);
    }

    public Coordenador update(Coordenador coordenador) {

        if (!repository.existsById(coordenador.getId())) {
            throw new NotFoundException(Coordenador.class.getSimpleName().toString(), coordenador.getId());
        }

        Coordenador coordenadorExistente = repository.findById(coordenador.getId()).get();

        // TODO: Ajustar pós Roles
        coordenadorExistente.getProfissional().setCpf(coordenador.getProfissional().getCpf());
        coordenadorExistente.getProfissional().setNome(coordenador.getProfissional().getNome());
        coordenadorExistente.getProfissional().setTelefone(coordenador.getProfissional().getTelefone());
        coordenadorExistente.setCursosGerenciados(coordenador.getCursosGerenciados());
        coordenadorExistente.getProfissional().setFoto(coordenador.getProfissional().getFoto());
        coordenadorExistente.getProfissional().setEmail(coordenador.getProfissional().getEmail());
        // coordenadorExistente.setStatus(coordenador.getStatus());

        return repository.saveAndFlush(coordenadorExistente);

    }

    public Coordenador updatePartial(Map<String, Object> mapValores, Integer id) {

        if (!repository.existsById(id)) {
            throw new NotFoundException(Coordenador.class.getSimpleName().toString(), id);
        }

        Coordenador coordenadorExistente = repository.findById(id).get();

        mapValores.forEach(
                (campo, valor) -> {
                    Field field = ReflectionUtils.findField(Coordenador.class, campo);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, coordenadorExistente, valor);
                });
        repository.saveAndFlush(coordenadorExistente);
        return coordenadorExistente;

    }

    // public Coordenador changeCoordenadorStatusAtivacao(Integer idCoordenador, Map<String, Integer> status) {
    //     Coordenador c = getOne(idCoordenador);
    //     // c.setStatusAtivacao(StatusAtivacao.toEnum(status.get("statusAtivacao")));
    //     return repository.saveAndFlush(c);
    // }

}