package br.com.systempus.systempus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systempus.systempus.domain.DisponibilidadeProfessor;

public interface DisponibilidadeProfessorRepository extends JpaRepository<DisponibilidadeProfessor, Integer> {
    
}
