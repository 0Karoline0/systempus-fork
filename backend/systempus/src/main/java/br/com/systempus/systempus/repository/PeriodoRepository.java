package br.com.systempus.systempus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systempus.systempus.domain.Periodo;

public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {

    // List<Periodo> findAllByCurso(Curso curso);

}
