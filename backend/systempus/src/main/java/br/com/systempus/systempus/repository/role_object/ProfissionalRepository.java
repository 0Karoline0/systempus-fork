package br.com.systempus.systempus.repository.role_object;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.systempus.systempus.domain.role_object.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {

    @Query(value = "SELECT COUNT(*) > 0 FROM pessoa WHERE cpf = :cpf", nativeQuery = true)
    boolean existsByCPF(@Param("cpf")String cpf);

    boolean existsByEmail(String email);

}
