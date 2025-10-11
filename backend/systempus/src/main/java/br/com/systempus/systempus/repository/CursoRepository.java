package br.com.systempus.systempus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systempus.systempus.domain.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{
    // List<Professor> getProfessoresByCurso(Integer id);
}
