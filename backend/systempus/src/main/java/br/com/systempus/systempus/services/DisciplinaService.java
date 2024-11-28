package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
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
import br.com.systempus.systempus.error.IllegalStateException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.CursoRepository;
import br.com.systempus.systempus.repository.DisciplinaRepository;
import br.com.systempus.systempus.services.interfaces.IDisciplinaService;

@Service
public class DisciplinaService implements IDisciplinaService {

	@Autowired
	private DisciplinaRepository repository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private HorarioDisciplinaService horarioDisciplinaService;

	@Override
	public Disciplina getOne(Integer id) {
		Disciplina resultado = repository.findById(id)
				.orElseThrow(() -> new NotFoundException(Disciplina.class.getSimpleName().toString(), id));
		return resultado;
	}

	@Override
	public List<DisciplinaDTO> getAll() {
		List<DisciplinaDTO> resultado = DisciplinaDTO.convertToDTO(repository.findAll());
		return resultado;
	}

	@Override
	public void save(Disciplina disciplina) {
		if (disciplina.getId() == null) {
			repository.save(disciplina);
		} else {
			throw new IllegalStateException(Disciplina.class.getSimpleName().toString());
		}
	}

	@Override
	public void delete(Integer id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new NotFoundException(Disciplina.class.getSimpleName().toString(), id);
		}
	}

	@Override
	public void update(Disciplina disciplina) {
		if (repository.existsById(disciplina.getId())) {
			Disciplina disciplinaExistente = repository.findById(disciplina.getId()).get();

			disciplinaExistente.setNome(disciplina.getNome());
			disciplinaExistente.setModulo(disciplina.getModulo());
			disciplinaExistente.setProfessores(disciplina.getProfessores());

			repository.saveAndFlush(disciplinaExistente);
		} else {
			throw new NotFoundException(Disciplina.class.getSimpleName().toString(), disciplina.getId());
		}
	}

	@Override
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

	public List<Disciplina> getByCurso(Integer idCurso) {

		List<Disciplina> disciplinas = new ArrayList<>();

		if (cursoRepository.existsById(idCurso)) {
			List<Modulo> modulos = cursoRepository.findById(idCurso).get().getModulos();

			if (!modulos.isEmpty()) {
				for (int i = 0; i < modulos.size(); i++) {
					disciplinas.addAll(modulos.get(i).getDisciplinas());
				}

				return disciplinas;
			} else {
				return disciplinas;
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

	public List<HorarioDisciplinaDTO> saveHorariosDisciplina(List<HorarioDisciplinaDTO> disponibilidadeRequest, Integer disciplinaId){
		Disciplina disciplina = getOne(disciplinaId);
	    return horarioDisciplinaService.save(disponibilidadeRequest, disciplina);
	}

	public List<HorarioDisciplinaDTO> updateHorariosDisciplina(Integer disciplinaId,
			List<HorarioDisciplinaDTO> horarios) {
		Disciplina disciplina = getOne(disciplinaId);
	    return horarioDisciplinaService.updateHorarioDisciplina(horarios, disciplina);
	}
	
}
