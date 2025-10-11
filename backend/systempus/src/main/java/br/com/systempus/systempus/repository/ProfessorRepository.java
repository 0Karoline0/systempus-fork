package br.com.systempus.systempus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systempus.systempus.domain.role_object.Professor;


public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

    // @Query(value = "SELECT COUNT(*) > 0 FROM profissional WHERE cpf = :cpf", nativeQuery = true)
    // boolean existsByCPF(@Param("cpf")String cpf);

    // boolean existsByEmail(String email);
}
