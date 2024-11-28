package br.com.systempus.systempus.services;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.CargaHoraria;
import br.com.systempus.systempus.domain.HorarioAula;
import br.com.systempus.systempus.domain.Periodo;
import br.com.systempus.systempus.error.DataIntegrityViolationException;
import br.com.systempus.systempus.repository.HorarioAulaRepository;

@Service
public class HorarioAulaService {

    @Autowired
    private HorarioAulaRepository repository;

    public HorarioAula getById(Integer id){
        return repository.findById(id).get();
    }

    public List<HorarioAula> getAll(){
        return repository.findAll();
    }

    private void save(List<HorarioAula> horariosAulas, HorarioAula horarioAula, CargaHoraria cargaHoraria){
        horarioAula.setCargaHoraria(cargaHoraria);
        repository.save(horarioAula);
        horariosAulas.add(horarioAula);
    }

    public List<HorarioAula> saveHorariosByPeriodo(CargaHoraria cargaHoraria, Periodo periodo){


        try {
            Set<LocalTime> horarios = separarCargasHorarias(cargaHoraria, periodo);
            List<LocalTime> horariosList = new ArrayList<>(horarios);
            List<HorarioAula> horariosAula = new ArrayList<>();
            int listSize = horariosList.size();

            // 08:10
            // 09:00
            //HorarioAula - 08: 10 --> 09:00
            // 09:50
            // 10:00
            // 10:50
            // 11:40

            for (int j = 0; j < listSize; j++){
                HorarioAula horarioAula = new HorarioAula();
                LocalTime horarioAfrente = periodo.getInicioHorario();

                if (j < listSize - 1){
                    horarioAfrente = horariosList.get(j + 1);
                }

                if (horariosList.get(j) != periodo.getInicioIntervalo()){
                    horarioAula.setInicioAula(horariosList.get(j));

                    //Horário antes do Intervalo
                    if (isIndex(listSize, j) && isHorarioAntesIntervalo(horarioAfrente, periodo.getInicioIntervalo())){
                        horarioAula.setFimAula(periodo.getInicioIntervalo());
                    } else if (j > 0 && isIndex(listSize, j) && (horariosList.get(j - 1) == periodo.getFimIntervalo())){
                        horarioAula.setFimAula(periodo.getFimIntervalo());
                    }

                    //Horário antes do encerramento das aulas
                    if (isIndex(listSize, j) && (j == listSize - 1)){
                        horarioAula.setFimAula(periodo.getFimHorario());
                    } else {
                        horarioAula.setFimAula(horarioAfrente);
                    }

                    this.save(horariosAula,horarioAula, cargaHoraria);
                }
            }


            return horariosAula;

        } catch (Exception e){
            throw new DataIntegrityViolationException(e.toString());
        }

        // if (cargaHoraRepository.existsById(horarioAula.getIdCargaHoraria()) && periodoRepository.existsById(horarioAula.getIdPeriodo())){
            // return repository.save(horarioAula);
        // }else{
            // throw new NotFoundException("Carga Horária ou Período não existem no banco de dados");
        // }
    }

    public Boolean isHorarioAntesIntervalo(LocalTime dataAfrente, LocalTime horarioInicioIntervalo){
        if (dataAfrente == horarioInicioIntervalo){
            return true;
        }
        return false;
    }

    public Boolean isIndex(int limiteLista, int indexAtual){
        if (indexAtual > limiteLista){
            return false;
        }
        return true;
    }

    // public HorarioAula update(HorarioAula horarioAula, Integer id){
    //     if (repository.existsById(id)){
    //         HorarioAula existente = repository.findById(id).get();

    //         return repository.saveAndFlush(existente);
    //     }else{
    //         return null;
    //     }
    // }

    // public void deleteById(Integer id){
    //     repository.deleteById(id);
    // }

    private Set<LocalTime> separarCargasHorarias(CargaHoraria carga, Periodo periodo){
        Integer duracaoAula = carga.getCargaHoraria();
        long primeiroTempo = Duration.between(periodo.getInicioHorario(), periodo.getInicioIntervalo()).toMinutes();
        long segundoTempo = Duration.between(periodo.getFimIntervalo(), periodo.getFimHorario()).toMinutes();
        long quantidadeAulas = (primeiroTempo + segundoTempo) / duracaoAula;

        Set<LocalTime> horarios = new TreeSet<>();

        LocalTime ultimoHorario = periodo.getInicioHorario();

        for (int i = 0; i < (primeiroTempo / duracaoAula); i++){
            horarios.add(ultimoHorario);
            ultimoHorario = ultimoHorario.plusMinutes(duracaoAula);
        }

        horarios.add(periodo.getInicioIntervalo());
        horarios.add(periodo.getFimIntervalo());
        ultimoHorario = periodo.getFimIntervalo();

        for (int i = 0; i < (segundoTempo / duracaoAula); i++){
            horarios.add(ultimoHorario);
            ultimoHorario = ultimoHorario.plusMinutes(duracaoAula);
        }


        System.out.println("Quantidade aulas: " + quantidadeAulas);
        for (LocalTime horario : horarios){
            System.out.println(horario);
        }

        return horarios;

    }

}
