package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.systempus.systempus.domain.Curso;
import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.HorarioDisciplina;
import br.com.systempus.systempus.domain.Modulo;
import br.com.systempus.systempus.domain.dto.DisciplinaDTO;
import br.com.systempus.systempus.domain.dto.HorarioDisciplinaDTO;
import br.com.systempus.systempus.error.DataIntegrityViolationException;
import br.com.systempus.systempus.error.IllegalStateException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.CursoRepository;
import br.com.systempus.systempus.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository repository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private HorarioDisciplinaService horarioDisciplinaService;


	public Disciplina getOne(Integer id) {
		Disciplina resultado = repository.findById(id)
				.orElseThrow(() -> new NotFoundException(Disciplina.class.getSimpleName().toString(), id));
		return resultado;
	}

	public DisciplinaDTO getDisciplinaById(Integer id) {
		final Disciplina resultado = repository.findById(id)
				.orElseThrow(() -> new NotFoundException(Disciplina.class.getSimpleName().toString(), id));
		List<DisciplinaDTO> lista = DisciplinaDTO.convertToDTO(Collections.singletonList(resultado));
		return lista.get(0);
	}


	public List<DisciplinaDTO> getAll() {
		List<DisciplinaDTO> resultado = DisciplinaDTO.convertToDTO(repository.findAll());
		return resultado;
	}

	public void save(Disciplina disciplina) {
		if (disciplina.getId() == null) {
			repository.save(disciplina);
		} else {
			throw new IllegalStateException(Disciplina.class.getSimpleName().toString());
		}
	}

	public void delete(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new NotFoundException(Disciplina.class.getSimpleName().toString(), id);
		}
	}

	public void update(Disciplina disciplina) {
		if (repository.existsById(disciplina.getId())) {
			Disciplina disciplinaExistente = repository.findById(disciplina.getId()).get();

			disciplinaExistente.setNome(disciplina.getNome());
			disciplinaExistente.setModulo(disciplina.getModulo());
			disciplinaExistente.setProfessoresDisciplinas(disciplina.getProfessoresDisciplinas());

			repository.saveAndFlush(disciplinaExistente);
		} else {
			throw new NotFoundException(Disciplina.class.getSimpleName().toString(), disciplina.getId());
		}
	}

	public Disciplina updatePartial(Map<String, Object> mapValores, Integer id) {
		if (repository.existsById(id)) {
			Disciplina disciplinaExistente = repository.findById(id).get();

			mapValores.forEach((campo, valor) -> {
				Field field = ReflectionUtils.findField(Disciplina.class, campo);
				field.setAccessible(true);
				ReflectionUtils.setField(field, disciplinaExistente, valor);
				field.setAccessible(false);
			});

			repository.saveAndFlush(disciplinaExistente);
			return disciplinaExistente;
		} else {
			throw new NotFoundException(Disciplina.class.getSimpleName().toString(), id);
		}
	}

	public List<DisciplinaDTO> getByCurso(Integer idCurso) {

		List<Disciplina> disciplinas = new ArrayList<>();

		if (cursoRepository.existsById(idCurso)) {
			List<Modulo> modulos = cursoRepository.findById(idCurso).get().getModulos();

			if (!modulos.isEmpty()) {
				for (int i = 0; i < modulos.size(); i++) {
					disciplinas.addAll(modulos.get(i).getDisciplinas());
				}

				return DisciplinaDTO.convertToDTO(disciplinas);
			} else {
				return DisciplinaDTO.convertToDTO(disciplinas);
			}
		} else {
			throw new NotFoundException(Curso.class.getSimpleName().toString(), idCurso);
		}

	}

	public List<HorarioDisciplinaDTO> getHorariosByDisciplina(Integer id) {
        Disciplina disciplina = getOne(id);
        List<HorarioDisciplina> horarios = disciplina.getHorarioDisciplina();
        return HorarioDisciplinaDTO.convertToDTO(horarios);
	}

	public List<HorarioDisciplinaDTO> saveHorariosDisciplina(List<HorarioDisciplinaDTO> horarios, Integer disciplinaId){
		horariosCondizCargas(horarios, disciplinaId);
		Disciplina disciplina = getOne(disciplinaId);
	    return horarioDisciplinaService.save(horarios, disciplina);
	}
	
	public List<HorarioDisciplinaDTO> updateHorariosDisciplina(Integer disciplinaId,
	List<HorarioDisciplinaDTO> horarios) {
		horariosCondizCargas(horarios, disciplinaId);
		Disciplina disciplina = getOne(disciplinaId);
	    return horarioDisciplinaService.updateHorarioDisciplina(horarios, disciplina);
	}

	public void horariosCondizCargas(List<HorarioDisciplinaDTO> horarios, Integer disciplinaId){
		Integer quantidadeCargas = getOne(disciplinaId).getQuantidadeCargas();
		if (horarios.size() != quantidadeCargas){
			throw new DataIntegrityViolationException("A quantidade de horários selecionados precisa ser equivalente à quantidade de carga horária da disciplina.");
		}
	}
	
}
