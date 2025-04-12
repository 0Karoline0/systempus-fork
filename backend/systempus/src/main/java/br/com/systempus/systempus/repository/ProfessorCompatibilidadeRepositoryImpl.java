package br.com.systempus.systempus.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.systempus.systempus.domain.dto.ProfessorCompatibilidadeDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ProfessorCompatibilidadeRepositoryImpl implements ProfessorCompatibilidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<ProfessorCompatibilidadeDTO> getProfessoresByCompatibilidade(Integer disciplinaId) {
        String sql = """
WITH Compatibilidade AS (
    SELECT 
        dp.professor_id, 
        COUNT(DISTINCT dp.horario_aula_id) AS compatibilidade
    FROM disponibilidade_professor dp
    JOIN horario_disciplina hd 
        ON dp.horario_aula_id = hd.horario_aula_id
        AND dp.dia_semana = hd.dia_semana  
    WHERE hd.disciplina_id = :disciplinaId
    GROUP BY dp.professor_id, dp.dia_semana
),

TotalHorarios AS (
    SELECT 
        hd.disciplina_id,
        COUNT(DISTINCT hd.horario_aula_id) AS total_horarios_disciplina
    FROM horario_disciplina hd
    WHERE hd.disciplina_id = :disciplinaId
    GROUP BY hd.disciplina_id
)

SELECT 
    p.id AS professor_id,
    prof.nome,
    prof.foto,
    prof.email,
    prof.telefone, -- Adicionando telefone do professor
    CAST(ROUND((COALESCE(SUM(c.compatibilidade), 0) * 100.0) / NULLIF(th.total_horarios_disciplina, 0), 2) AS DOUBLE PRECISION) AS compatibilidade_percent,
    CASE 
        WHEN COALESCE(SUM(c.compatibilidade), 0) = 0 THEN 'Horário Indisponível'
        WHEN COALESCE(SUM(c.compatibilidade), 0) >= COALESCE(th.total_horarios_disciplina, 0) THEN 'Horário Completo'
        ELSE 'Horário Parcial'
    END AS status_horario,
    CASE 
        WHEN preferencias.pref_count IS NOT NULL THEN 'Preferida'
        ELSE 'Neutro'
    END AS status_preferencia
FROM professor p
JOIN profissional prof ON p.id = prof.id
LEFT JOIN Compatibilidade c ON p.id = c.professor_id
LEFT JOIN TotalHorarios th ON th.disciplina_id = :disciplinaId
LEFT JOIN (
    SELECT ppd.prof_id, COUNT(ppd.disc_id) AS pref_count
    FROM preferencia_professor_disciplina ppd
    WHERE ppd.disc_id = :disciplinaId
    GROUP BY ppd.prof_id
) AS preferencias ON p.id = preferencias.prof_id
WHERE p.id IN (
    SELECT dp.professor_id FROM disponibilidade_professor dp
    JOIN horario_disciplina hd ON dp.horario_aula_id = hd.horario_aula_id
    WHERE hd.disciplina_id = :disciplinaId
    UNION
    SELECT p.id FROM professor p
    JOIN professor_curso cp ON p.id = cp.professor_id
    WHERE cp.curso_id = (SELECT curso_id FROM disciplina WHERE id = :disciplinaId)
)
GROUP BY p.id, prof.nome, prof.foto, prof.email, prof.telefone, th.total_horarios_disciplina, preferencias.pref_count
ORDER BY 
    CASE 
        WHEN preferencias.pref_count IS NOT NULL THEN 1
        ELSE 2
    END,
    compatibilidade_percent DESC;
        """;                
    
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("disciplinaId", disciplinaId); // Adicionando o parâmetro corretamente
        List<Object[]> results = query.getResultList();
    
        List<ProfessorCompatibilidadeDTO> dtos = results.stream().map(row -> 
            new ProfessorCompatibilidadeDTO(
                (Integer) row[0],
                (String) row[1],
                (String) row[2],
                (String) row[3],
                (String) row[4],
                (Double) row[5],
                (String) row[6],
                (String) row[7]
            )
        ).toList();
    
        return dtos;
    }
    
}
