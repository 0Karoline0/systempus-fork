package br.com.systempus.systempus.services.role_object;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.dto.ProfissionalDTO;
import br.com.systempus.systempus.domain.enumerador.ProfissionalEnum;
import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import br.com.systempus.systempus.domain.role_object.Profissional;
import br.com.systempus.systempus.error.DataIntegrityViolationException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.role_object.ProfissionalRepository;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository repository;

    // @Autowired
    // private ProfissionalRoleService profissionalRoleService;

    public List<Profissional> getAllByTipoProfissional(ProfissionalEnum profissional) {
        List<Profissional> profissionais = repository.findAll();
        return profissionais.stream().filter(p -> p.hasRoleOf(profissional)).toList();
    }

    public Profissional getProfissionalById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Profissional não encontrado!"));
    }

    public void existsByCpf(String cpf) {
        if (repository.existsByCPF(cpf)) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
    }

    public void existsByEmail(String email) {
        if (repository.existsByEmail(email)) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        };
    }

    public Profissional save(Profissional profissional) {
        existsByCpf(profissional.getCpf());
        existsByEmail(profissional.getEmail());
        return repository.save(profissional);
    }

    public void deleteById(Integer id){
        Profissional p = getProfissionalById(id);
        p.getRoles().clear();
        repository.deleteById(id);
    }

    public Profissional update(Profissional profissional) {
        Profissional p = getProfissionalById(profissional.getId());
        return repository.saveAndFlush(p);
    }

    public Profissional mudarStatusAtivacao(Integer idProfissional, StatusAtivacao statusAtivacao) {
        Profissional p = getProfissionalById(idProfissional);
        p.setStatusAtivacao(statusAtivacao);
        return repository.saveAndFlush(p);
    }

}