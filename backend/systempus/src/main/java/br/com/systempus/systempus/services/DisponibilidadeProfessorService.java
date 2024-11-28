package br.com.systempus.systempus.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
import br.com.systempus.systempus.domain.HorarioAula;
import br.com.systempus.systempus.domain.Professor;
import br.com.systempus.systempus.domain.dto.DisponibilidadeProfessorDTO;
import br.com.systempus.systempus.repository.DisponibilidadeProfessorRepository;
import jakarta.transaction.Transactional;

@Service
public class DisponibilidadeProfessorService {

    @Autowired
    private DisponibilidadeProfessorRepository repository;

    @Autowired
    private HorarioAulaService horarioAulaService;

    public DisponibilidadeProfessor getById(Integer id){
        return repository.findById(id).get();
    }

    public List<DisponibilidadeProfessor> getAll(){
        return repository.findAll();
    }

    @Transactional
    public List<DisponibilidadeProfessorDTO> save(List<DisponibilidadeProfessorDTO> disponibilidades, Professor professor){

        List<DisponibilidadeProfessorDTO> disponibilidadesRetorno = new ArrayList<>();

        for (DisponibilidadeProfessorDTO disponibilidade : disponibilidades){
            HorarioAula horario = horarioAulaService.getById(disponibilidade.getHorarioAulaId());
            DisponibilidadeProfessor d = new DisponibilidadeProfessor(professor, horario, disponibilidade.getDiaSemana());
            disponibilidadesRetorno.add(disponibilidade);
            repository.save(d);
        }

        return disponibilidadesRetorno;
    }

    @Transactional
    public List<DisponibilidadeProfessorDTO> updateDisponibilidades(List<DisponibilidadeProfessorDTO> disponibilidades, Professor professor){
        deleteDisponibilidadesByProfessor(professor);
        return save(disponibilidades, professor);
    }

    @Transactional
    public void deleteDisponibilidadesByProfessor(Professor professor){
        repository.deleteByProfessor(professor);
    }

}
