package br.com.systempus.systempus.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
import br.com.systempus.systempus.domain.HorarioAula;
import br.com.systempus.systempus.domain.Professor;
import br.com.systempus.systempus.domain.dto.DisponibilidadeProfessorDTO;
import br.com.systempus.systempus.domain.embeddableclass.DisponibilidadeProfessorId;
import br.com.systempus.systempus.repository.DisciplinaRepository;
import br.com.systempus.systempus.repository.DisponibilidadeProfessorRepository;
import br.com.systempus.systempus.repository.HorarioAulaRepository;
import jakarta.transaction.Transactional;

@Service
public class DisponibilidadeProfessorService {
    
    @Autowired
    private DisponibilidadeProfessorRepository repository;

    @Autowired
    private HorarioAulaService horarioAulaService;

    // public DisponibilidadeProfessor getById(Integer id){
    //     return repository.findById(id).get();
    // }

    public List<DisponibilidadeProfessor> getAll(){
        return repository.findAll();
    }

    @Transactional
    public List<DisponibilidadeProfessorDTO> save(List<DisponibilidadeProfessorDTO> disponibilidades, Professor professor){

        List<DisponibilidadeProfessorDTO> disponibilidadesRetorno = new ArrayList<DisponibilidadeProfessorDTO>();

        for (DisponibilidadeProfessorDTO disponibilidade : disponibilidades){
            HorarioAula horario = horarioAulaService.getById(disponibilidade.getHorarioAulaId());
            // DisponibilidadeProfessorId id = new DisponibilidadeProfessorId(horario.getId(), professor.getId());
            DisponibilidadeProfessor d = new DisponibilidadeProfessor(professor, horario, disponibilidade.getDiaSemana());
            disponibilidadesRetorno.add(disponibilidade);
            repository.save(d);
        }

        return disponibilidadesRetorno;
    }


}
