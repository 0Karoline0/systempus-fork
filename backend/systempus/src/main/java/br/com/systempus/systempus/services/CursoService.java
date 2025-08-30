package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.systempus.systempus.domain.Curso;
import br.com.systempus.systempus.domain.Modulo;
import br.com.systempus.systempus.domain.Professor;
import br.com.systempus.systempus.domain.dto.CursoDTO;
import br.com.systempus.systempus.error.IllegalStateException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.CoordenadorRepository;
import br.com.systempus.systempus.repository.CursoRepository;
import br.com.systempus.systempus.repository.ModuloRepository;
import br.com.systempus.systempus.repository.PeriodoRepository;
import br.com.systempus.systempus.services.interfaces.ICursoService;

@Service
public class CursoService implements ICursoService {

    @Autowired//Instanciação automática
    private CursoRepository repository;

    @Autowired//Instanciação automática
    private CoordenadorRepository coordenadorRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private PeriodoRepository periodoRepository;

    @Autowired
    private ProfessorService professorService;

    @Override
	public List<CursoDTO> getAll() {
        List<Curso> resultado = repository.findAll();
        List<CursoDTO> cursos = CursoDTO.convertToDTO(resultado);
        return cursos;
    }

    @Override
	public Curso getOne(Integer id) {
        Curso resultado = repository.findById(id).orElseThrow(() -> new NotFoundException(Curso.class.getSimpleName().toString(), id));
        // System.out.println("\n\n\n\n Teste: " + resultado.getPeriodos() + "\n\n\n\n");
        return resultado;
    }

    @Override
	public void save(Curso curso) {
        if (curso.getId() == null) {
            repository.save(curso);
        } else {
            throw new IllegalStateException(Curso.class.getSimpleName().toString());
        }
    }

    @Override
	public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(Curso.class.getSimpleName().toString(), id);
        }
    }

//Segue corretamente o conceito de POO
//Problema ----> Exemplo: Um vendedor pede os seus documentos
    @Override
	public void update(Curso curso) {
        if (repository.existsById(curso.getId())) {
            Curso cursoExistente = repository.findById(curso.getId()).get();

            cursoExistente.setNome(curso.getNome());
            cursoExistente.setNivelEnsino(curso.getNivelEnsino());
            cursoExistente.setQtdPeriodos(curso.getQtdPeriodos());
            cursoExistente.setModalidade(curso.getModalidade());
            cursoExistente.setCargaTotal(curso.getCargaTotal());
            cursoExistente.setCoordenador(curso.getCoordenador());
            cursoExistente.setModulos(curso.getModulos());

            repository.saveAndFlush(cursoExistente);

        } else {
            throw new NotFoundException(Curso.class.getSimpleName().toString(), curso.getId());
        }
    }

//Não Segue corretamente o ceonceito de POO [Por causa da reflexão]
//Problema ----> Exemplo: Um vendedor chega e já pega os documentos da pessoa sem autorização
    @Override
	public Curso updatePartial(Map<String, Object> mapValores, Integer id) {
        if (repository.existsById(id)) {
            Curso cursoExistente = repository.findById(id).get();

            mapValores.forEach(
                    (campo, valor) -> {
                        Field field = ReflectionUtils.findField(Curso.class, campo);
                        field.setAccessible(true);

                        if (!(field.getType().isEnum())) {
                            ReflectionUtils.setField(field, cursoExistente, valor);
                            field.setAccessible(false);
                        } else {

                            try {
                                Class<?> enumClass = field.getType();

                                Method valueOfMethod = enumClass.getMethod("toEnum", Integer.class);
                                Enum<?> enumValor = (Enum<?>) valueOfMethod.invoke(null, (Integer) valor);

                                ReflectionUtils.setField(field, cursoExistente, enumValor);
                                field.setAccessible(false);
                            } catch (NoSuchMethodException ex) {
                                throw new NotFoundException("Método não encontrado no enum toEnum");
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    });

            System.out.println(cursoExistente);

            repository.saveAndFlush(cursoExistente);
            return cursoExistente;
        } else {
            throw new NotFoundException(Curso.class.getSimpleName().toString(), id);
        }
    }

    public Curso adicionarModulo(Integer idCurso, Modulo modulo){
        Curso curso = repository.findById(idCurso).get();
        modulo.setCurso(curso);

        return repository.findById(idCurso).get();
    }

    public List<Professor> getProfessoresByCurso(Integer idCurso){
        return repository.findById(idCurso).get().getProfessores();
    }

    public List<Professor> getProfessoresSemHorarios(int idCurso){
        Curso cursos = repository.findById(idCurso).get();

        List<Professor> professores = cursos.getProfessores();

        List<Professor> semHorarios = new ArrayList<>();
        for (Professor professor : professores) {
            if (professor.getDisponibilidadeProfessor().isEmpty()) {
                semHorarios.add(professor);
            }
        }
        return semHorarios;

    }

    public List<CursoDTO> getCursosByProfessor(Integer idProfessor) {
        return CursoDTO.convertToDTO(professorService.getOne(idProfessor).getCursos());
    }

    public List<Curso> getCursosByIntegerList(List<Integer> cursos) {
        List<Curso> aux = new ArrayList<>();
        for (Integer idCurso : cursos) {
            aux.add(getOne(idCurso));
        }
        return aux;
    }


}