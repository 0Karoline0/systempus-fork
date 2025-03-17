package br.com.systempus.systempus.repository;

import java.util.List;

import br.com.systempus.systempus.domain.dto.ProfessorCompatibilidadeDTO;

public interface ProfessorCompatibilidadeRepository {
    List<ProfessorCompatibilidadeDTO> getProfessoresByCompatibilidade(Integer disciplinaId);
}
