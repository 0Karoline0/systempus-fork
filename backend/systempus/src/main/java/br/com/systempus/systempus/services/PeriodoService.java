package br.com.systempus.systempus.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import br.com.systempus.systempus.domain.CargaHoraria;
import br.com.systempus.systempus.domain.Curso;
import br.com.systempus.systempus.domain.HorarioAula;
import br.com.systempus.systempus.domain.Periodo;
import br.com.systempus.systempus.error.DataIntegrityViolationException;
import br.com.systempus.systempus.error.NotFoundException;
import br.com.systempus.systempus.repository.CargaHorariaRepository;
import br.com.systempus.systempus.repository.PeriodoRepository;
import br.com.systempus.systempus.util.Util;
import jakarta.transaction.Transactional;

@Service
public class PeriodoService {

    @Autowired
    private PeriodoRepository repository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CargaHorariaRepository cargaRepository;

    @Autowired
    private HorarioAulaService horarioAulaService;

    public Periodo getOne(Integer id) {
        Periodo resultado = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Periodo.class.getSimpleName().toString(), id));
        return resultado;
    }

    public List<Periodo> getAll() {
        List<Periodo> resultado = repository.findAll();
        return resultado;
    }

    @Transactional
    public void save(Periodo periodo) {

        CargaHoraria cargaHoraria = cargaRepository.findById(3).get();

        Util.matchTurno(periodo);
        Util.matchCargaHoraria(periodo, cargaHoraria);
        Util.podeCadastrarPorTurno(periodo, getAll());

        try {
            List<HorarioAula> horariosAula = horarioAulaService.saveHorariosByPeriodo(cargaHoraria, periodo);
            periodo.setHorariosAula(horariosAula);
            repository.save(periodo);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Não foi possível salvar Horários Aulas: " + e.toString());
        }

    }

    public void delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(Periodo.class.getSimpleName().toString(), id);
        }
    }

    public void update(Periodo periodo, Integer id){

        CargaHoraria cargaHoraria = cargaRepository.findById(3).get();

        Util.matchTurno(periodo);

        if(repository.existsById(id)){
            Util.matchCargaHoraria(periodo, cargaHoraria);

                Periodo periodoExistente = repository.findById(id).get();
                // periodo.setCurso(periodoExistente.getCurso());

                // turnoCadastrado(periodo);

                periodoExistente.setTurno(periodo.getTurno());
                periodoExistente.setInicioIntervalo(periodo.getInicioIntervalo());
                periodoExistente.setFimIntervalo(periodo.getFimIntervalo());
                periodoExistente.setInicioHorario(periodo.getInicioHorario());
                periodoExistente.setFimHorario(periodo.getFimHorario());

                repository.saveAndFlush(periodoExistente);

        }else{
            throw new NotFoundException(Periodo.class.getSimpleName().toString(), periodo.getId());
        }
    }

    public Periodo updatePartial(Map<String, Object> mapValores, Integer id) {
        if (repository.existsById(id)) {
            Periodo periodoExistente = repository.findById(id).get();

            mapValores.forEach(
                    (campo, valor) -> {
                        Field field = ReflectionUtils.findField(Periodo.class, campo);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, periodoExistente, valor);
                        field.setAccessible(false);
                    });

            repository.saveAndFlush(periodoExistente);
            return periodoExistente;
        } else {
            throw new NotFoundException(Periodo.class.getSimpleName().toString(), id);
        }
    }

    public List<Periodo> getPeriodosByCurso(Integer idCurso) {
        Curso cursoExistente = cursoService.getOne(idCurso);
        System.out.println(cursoExistente.getPeriodos().size());
        for (Periodo periodo : cursoExistente.getPeriodos()){
            System.out.println(periodo);
        }

        List<Periodo> periodos = cursoExistente.getPeriodos();
        return periodos;
    }



    // private void turnoCadastrado(Periodo periodo){
    // List<Periodo> periodos = repository.findAllByCurso(periodo.getCurso());

    // for (Periodo p : periodos){
    // if (p.getTurno() == periodo.getTurno()){
    // throw new
    // DataIntegrityViolationException(DataIntegrityViolationException.turnoAlreadyExists(periodo.getTurno().getTurno(),
    // periodo.getCurso().getNome()));
    // }
    // }

    // }


}
