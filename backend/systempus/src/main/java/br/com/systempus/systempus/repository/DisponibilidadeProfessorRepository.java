package br.com.systempus.systempus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
import br.com.systempus.systempus.domain.Professor;

public interface DisponibilidadeProfessorRepository extends JpaRepository<DisponibilidadeProfessor, Integer> {

    List<DisponibilidadeProfessor> getByProfessor(Professor professor);
    List<DisponibilidadeProfessor> deleteByProfessor(Professor professor);

}
