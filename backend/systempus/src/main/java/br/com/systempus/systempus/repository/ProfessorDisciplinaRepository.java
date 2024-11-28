package br.com.systempus.systempus.repository;

import br.com.systempus.systempus.domain.ProfessorDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorDisciplinaRepository extends JpaRepository<ProfessorDisciplina, Integer> {
    
}
