package br.com.systempus.systempus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.systempus.systempus.domain.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

    @Query(value = "SELECT COUNT(*) > 0 FROM profissional WHERE cpf = :cpf", nativeQuery = true)
    boolean existsByCPF(@Param("cpf")String cpf);

    boolean existsByEmail(String email);
}
