package br.com.systempus.systempus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.systempus.systempus.domain.Coordenador;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Integer>{

    @Query(value = "SELECT COUNT(*) > 0 FROM profissional WHERE cpf = :cpf", nativeQuery = true)
    boolean existsByCPF(@Param("cpf") String cpf);

    boolean existsByEmail(String email);
}
