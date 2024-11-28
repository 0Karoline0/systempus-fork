package br.com.systempus.systempus.services.interfaces;

import java.util.List;
import java.util.Map;

import br.com.systempus.systempus.domain.Disciplina;
import br.com.systempus.systempus.domain.dto.DisciplinaDTO;

public interface IDisciplinaService {
    public List<DisciplinaDTO> getAll();

    public Disciplina getOne(Integer id);

    public void save(Disciplina disciplina);

    public void delete(Integer id);

    public void update(Disciplina disciplina);

    public Disciplina updatePartial(Map<String, Object> mapValores, Integer id);
}
