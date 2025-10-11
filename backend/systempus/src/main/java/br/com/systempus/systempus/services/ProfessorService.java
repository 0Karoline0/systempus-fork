package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.systempus.systempus.domain.Curso;
import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
import br.com.systempus.systempus.domain.dto.DisciplinaDTO;
import br.com.systempus.systempus.domain.dto.DisponibilidadeProfessorDTO;
import br.com.systempus.systempus.domain.dto.ProfessorCompatibilidadeDTO;
import br.com.systempus.systempus.domain.dto.ProfissionalDTO;
import br.com.systempus.systempus.domain.dto.professor.ProfessorDTO;
import br.com.systempus.systempus.domain.enumerador.ProfissionalEnum;
import br.com.systempus.systempus.domain.enumerador.StatusAtivacao;
import br.com.systempus.systempus.error.DataIntegrityViolationException;
import br.com.systempus.systempus.error.IllegalStateException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.CursoRepository;
import br.com.systempus.systempus.repository.ProfessorCompatibilidadeRepository;
import br.com.systempus.systempus.repository.ProfessorRepository;
import br.com.systempus.systempus.services.role_object.ProfissionalRoleService;
import br.com.systempus.systempus.services.role_object.ProfissionalService;
import jakarta.transaction.Transactional;
import br.com.systempus.systempus.domain.role_object.Professor;
import br.com.systempus.systempus.domain.role_object.Profissional;
import br.com.systempus.systempus.domain.role_object.ProfissionalRole;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private DisciplinaService disciplinaService;

    @Autowired
    private UserTokenService tokenService;

    @Autowired
    private ProfissionalService profissionalService;

    @Autowired
    private ProfissionalRoleService profissionalRoleService;

    @Autowired
    private CursoRepository cursoRepository;

    private ProfessorCompatibilidadeRepository compatibilidade;

    ProfessorService(ProfessorCompatibilidadeRepository professorCompat){
        this.compatibilidade = professorCompat;
    }

    public void isProfessor(Profissional profissional) {
        if (!profissional.hasRoleOf(ProfissionalEnum.PROFESSOR)) {
            throw new NotFoundException(Professor.class.getSimpleName());
        }
    }

	public List<ProfessorDTO> getAll() {
        List<Profissional> p = profissionalService.getAllByTipoProfissional(ProfissionalEnum.PROFESSOR);
        return ProfessorDTO.convertList(p);
    }

	public Professor getOne(Integer id) {
        Profissional p = profissionalService.getProfissionalById(id);
        Professor professor = (Professor) p.getByRole(ProfissionalEnum.PROFESSOR);
        return professor;
    }

	public void delete(Integer id){
        Profissional p = profissionalService.getProfissionalById(id);
        Professor prof = (Professor) p.getByRole(ProfissionalEnum.PROFESSOR);
        for (ProfissionalRole papel : p.getRoles()) {
            if (papel.getTipoProfissional() == ProfissionalEnum.PROFESSOR) {
                p.getRoles().remove(papel);
            }
        }
        repository.deleteById(prof.getId());
        profissionalService.update(p);
    }

    @Transactional
    public Professor save(Professor professor) {
        return repository.save(professor);
    }

    @Transactional
    public void update(ProfessorDTO professor) {
        Profissional p = profissionalService.getProfissionalById(professor.getId());
        p.setNome(professor.getNome());
        p.setCpf(professor.getCpf());
        p.setEmail(professor.getEmail());
        p.setFoto(professor.getFoto());
        p.setTelefone(professor.getTelefone());
        p.setStatusAtivacao(professor.getStatusAtivacao());

        Professor professorRole = (Professor) p.getByRole(ProfissionalEnum.PROFESSOR);

        professorRole.setCursosLecionados(professor.getCursosLecionados().stream().map(
            c -> cursoRepository.findById(c.getId()).get()
        ).collect(Collectors.toList()));

        repository.saveAndFlush(professorRole);
        
        Profissional p2 = profissionalService.update(p);
    }

    public ProfessorDTO updatePartial(Map<String, Object> mapValores, Integer id) {
        Profissional p = profissionalService.getProfissionalById(id);
        isProfessor(p);

        mapValores.forEach(
            (campo, valor)->{
                Field field = ReflectionUtils.findField(Profissional.class, campo);
                field.setAccessible(true);
                ReflectionUtils.setField(field, p, valor);
                field.setAccessible(false);
            }
        );
        
        Professor professorRole = (Professor) p.getByRole(ProfissionalEnum.PROFESSOR);
        professorRole.setCursosLecionados(professorRole.getCursosLecionados().stream().map(
            c -> cursoRepository.findById(c.getId()).get()
        ).collect(Collectors.toList()));

        repository.saveAndFlush(professorRole);
        return ProfessorDTO.convertToDTO(profissionalService.update(p));
    }

    public List<DisponibilidadeProfessorDTO> getDisponibilidadeByProfessorId(Integer id) {
        Professor professor = getOne(id);
        List<DisponibilidadeProfessor> getDisponibilidades =  professor.getDisponibilidadeProfessor();
        return DisponibilidadeProfessorDTO.convertToDTO(getDisponibilidades);
    }

    public Professor salvarDisciplinasPreferidas(Integer id, List<Integer> disciplinasIds) {
        Set<Disciplina> disciplinas = new HashSet<>();

        for (Integer disciplinaId : disciplinasIds) {
            Disciplina dis = disciplinaService.getOne(disciplinaId);
            disciplinas.add(dis);
        }
        Professor professor = getOne(id);
        professor.setDisciplinasPreferidas(new HashSet<>());
        update(ProfessorDTO.convertToDTO(professor.getProfissional()));
        professor.setDisciplinasPreferidas(disciplinas);
        update(ProfessorDTO.convertToDTO(professor.getProfissional()));
        return professor;
    }

    public Set<Disciplina> getDisciplinasPreferidas(Integer idProfessor) {
        Professor professor = getOne(idProfessor);
        return professor.getDisciplinasPreferidas();
    }

    public List<ProfessorCompatibilidadeDTO> getProfessoresPorCompatibilidade(Integer disciplinaId){
        return compatibilidade.getProfessoresByCompatibilidade(disciplinaId);
    }


}
