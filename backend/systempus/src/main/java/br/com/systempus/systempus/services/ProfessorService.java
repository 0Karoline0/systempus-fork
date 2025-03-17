package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
import br.com.systempus.systempus.domain.Professor;
import br.com.systempus.systempus.domain.dto.DisciplinaDTO;
import br.com.systempus.systempus.domain.dto.DisponibilidadeProfessorDTO;
import br.com.systempus.systempus.domain.dto.ProfessorCompatibilidadeDTO;
import br.com.systempus.systempus.error.DataIntegrityViolationException;
import br.com.systempus.systempus.error.IllegalStateException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.ProfessorCompatibilidadeRepository;
import br.com.systempus.systempus.repository.ProfessorRepository;
import br.com.systempus.systempus.services.interfaces.IProfessorService;

@Service
public class ProfessorService implements IProfessorService{

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private DisponibilidadeProfessorService disponibilidadeService;

    @Autowired
    private DisciplinaService disciplinaService;

    private final ProfessorCompatibilidadeRepository compatibilidade;

    ProfessorService(ProfessorCompatibilidadeRepository professorCompat){
        this.compatibilidade = professorCompat;
    }

    @Override
	public List<Professor> getAll() {
        List<Professor> resultado = repository.findAll();
        return resultado;
    }

    @Override
	public Professor getOne(Integer id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(Professor.class.getSimpleName().toString(), id));
    }

    @Override
	public void save(Professor professor){
        if (professor.getId() == null){
            if (!repository.existsByCPF(professor.getCpf())){
                repository.save(professor);
            }else{
                throw new DataIntegrityViolationException(DataIntegrityViolationException.cpfExists(professor.getCpf()));
            }
        }else{
            throw new IllegalStateException(Professor.class.getSimpleName().toString());
        }
    }

    @Override
	public void delete(Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new NotFoundException(Professor.class.getSimpleName().toString(), id);
        }
    }

    @Override
	public void update(Professor professor){
        if(repository.existsById(professor.getId())){
            Professor professorExistente = repository.findById(professor.getId()).get();//Pegar Professor existente no repository

            professorExistente.setCpf(professor.getCpf());//Setar CPF do Professor Existente
            professorExistente.setNome(professor.getNome());
            professorExistente.setTelefone(professor.getTelefone());
            professorExistente.setProfessorDisciplina(professor.getProfessorDisciplina());
            professorExistente.setCursos(professor.getCursos());
            professorExistente.setStatus(professor.getStatus());
            professorExistente.setEmail(professor.getEmail());
            professorExistente.setFoto(professor.getFoto());

            repository.saveAndFlush(professorExistente);
        }else{
            throw new NotFoundException(Professor.class.getSimpleName().toString(), professor.getId());
        }
    }


    @Override
	public Professor updatePartial(Map<String, Object> mapValores, Integer id){//Conjunto de valores
        if(repository.existsById(id)){
            Professor professorExistente = repository.findById(id).get();//Pegar Professor existente no repository

            mapValores.forEach(//Transita pela classe professor passada
                (campo, valor)->{//Parâmetros referentes ao campo a ser preenchido e aos valores [String, Professor]
                    Field field = ReflectionUtils.findField(Professor.class, campo);//Classe Field procura o campo passado na classe professor
                    field.setAccessible(true);//Classe Field abre o acesso ao atributo da classe[public]
                    ReflectionUtils.setField(field, professorExistente, valor);//Passa os parâmetros [atributo da classe, classe desejada, valor desejado para o atributo]
                    field.setAccessible(false);//Classe Field fecha o acesso ao atributo da classe[private]
                }
            );

            repository.saveAndFlush(professorExistente);
            return professorExistente;
        }else{
            throw new NotFoundException(Professor.class.getSimpleName().toString(), id);
        }
    }

    public List<DisponibilidadeProfessorDTO> saveDisponibilidades(List<DisponibilidadeProfessorDTO> disponibilidadeRequest, Integer professorId) {
        Professor professor = getOne(professorId);
        return disponibilidadeService.save(disponibilidadeRequest, professor);
    }

    public List<DisponibilidadeProfessorDTO> getDisponibilidadeByProfessorId(Integer id) {
        Professor professor = getOne(id);
        List<DisponibilidadeProfessor> getDisponibilidades = professor.getDisponibilidadeProfessor();
        return DisponibilidadeProfessorDTO.convertToDTO(getDisponibilidades);
    }

    public List<DisponibilidadeProfessorDTO> updateDisponibilidadeProfessor(Integer professorId, List<DisponibilidadeProfessorDTO> disponibilidades){
        Professor professor = getOne(professorId);
        return disponibilidadeService.updateDisponibilidades(disponibilidades, professor);
    }

    public Professor salvarDisciplinasPreferidas(Integer id, List<Integer> disciplinasIds) {
        Set<Disciplina> disciplinas = new HashSet<>();

        for (Integer disciplinaId : disciplinasIds) {
            Disciplina dis = disciplinaService.getOne(disciplinaId);
            disciplinas.add(dis);
        }

        Professor professor = getOne(id);
        professor.setDisciplinasPreferidas(new HashSet<>());
        update(professor);
        professor.setDisciplinasPreferidas(disciplinas);
        update(professor);

        return professor;
    }

    public List<DisciplinaDTO> getDisciplinasPreferidas(Integer idProfessor) {

        Professor professor = getOne(idProfessor);

        List<DisciplinaDTO> disciplinas = new ArrayList<>();
        disciplinas = DisciplinaDTO.convertToDTO(new ArrayList<>(professor.getDisciplinasPreferidas()));

        return disciplinas;
    }

    public List<ProfessorCompatibilidadeDTO> getProfessoresPorCompatibilidade(Integer disciplinaId){
        return compatibilidade.getProfessoresByCompatibilidade(disciplinaId);
    }


}
