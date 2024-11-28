package br.com.systempus.systempus.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.HorarioAula;
import br.com.systempus.systempus.domain.HorarioDisciplina;
import br.com.systempus.systempus.domain.Professor;
import br.com.systempus.systempus.domain.dto.DisponibilidadeProfessorDTO;
import br.com.systempus.systempus.domain.dto.HorarioDisciplinaDTO;
import br.com.systempus.systempus.repository.HorarioDisciplinaRepository;
import jakarta.transaction.Transactional;

@Service
public class HorarioDisciplinaService {

    @Autowired
    private HorarioDisciplinaRepository repository;
    
    @Autowired
    private HorarioAulaService horarioAulaService;

    public List<HorarioDisciplinaDTO> getAll(){
        return HorarioDisciplinaDTO.convertToDTO(repository.findAll());
    }

    @Transactional
    public List<HorarioDisciplinaDTO> save(List<HorarioDisciplinaDTO> horarios, Disciplina disciplina){

        List<HorarioDisciplinaDTO> horariosRetorno = new ArrayList<>();

        for (HorarioDisciplinaDTO horario : horarios){
            HorarioAula horarioAula = horarioAulaService.getById(horario.getHorarioAulaId());
            HorarioDisciplina horarioDisciplina = new HorarioDisciplina(horario.getDiaSemana(), horarioAula, disciplina);
            horariosRetorno.add(horario);
            repository.save(horarioDisciplina);
        }

        return horariosRetorno;
    }
    
    @Transactional
    public List<HorarioDisciplinaDTO> updateHorarioDisciplina(List<HorarioDisciplinaDTO> disponibilidades, Disciplina disciplina){
        deleteHorariosByDisciplina(disciplina);
        return save(disponibilidades, disciplina);
    }

    @Transactional
    public void deleteHorariosByDisciplina(Disciplina disciplina){
        repository.deleteByDisciplina(disciplina);
    }

}
