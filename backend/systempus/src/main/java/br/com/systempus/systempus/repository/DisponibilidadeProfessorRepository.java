package br.com.systempus.systempus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systempus.systempus.domain.DisponibilidadeProfessor;
import br.com.systempus.systempus.domain.role_object.Professor;

public interface DisponibilidadeProfessorRepository extends JpaRepository<DisponibilidadeProfessor, Integer> {

    List<DisponibilidadeProfessor> getByProfessor(Professor professor);
    void deleteByProfessor(Professor professor);

}
