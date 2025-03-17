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
                    COALESCE(horarios.professor_id, preferencias.prof_id) AS professor_id, 
                    CAST((COALESCE(horarios.compatibilidade, 0) * 10) + 
                    COALESCE(preferencias.pref_count, 0) AS INTEGER) AS compatibilidade
                FROM (
                    SELECT 
                        dp.professor_id, 
                        COUNT(dp.horario_aula_id) AS compatibilidade
                    FROM disponibilidade_professor dp
                    JOIN horario_disciplina hd 
                        ON dp.horario_aula_id = hd.horario_aula_id
                        AND dp.dia_semana = hd.dia_semana  
                    WHERE hd.disciplina_id = :disciplinaId
                    GROUP BY dp.professor_id
                ) AS horarios
                FULL OUTER JOIN (
                    SELECT 
                        ppd.prof_id, 
                        COUNT(ppd.disc_id) AS pref_count
                    FROM preferencia_professor_disciplina ppd
                    WHERE ppd.disc_id = :disciplinaId
                    GROUP BY ppd.prof_id
                ) AS preferencias 
                ON horarios.professor_id = preferencias.prof_id
            )
        
            SELECT 
                p.id AS professor_id,
                prof.nome,
                prof.foto,
                prof.email,
                CAST(prof.status_profissional AS INTEGER) AS status_profissional,
                COALESCE(CAST(c.compatibilidade AS INTEGER), 0) AS compatibilidade,
                CAST(ROUND((COALESCE(c.compatibilidade, 0) * 100.0) / MAX(c.compatibilidade) OVER(), 2) AS DOUBLE PRECISION) AS compatibilidade_percent,
                CASE 
                    WHEN COALESCE(c.compatibilidade, 0) >= COALESCE(horarios.total_horarios, 0) THEN 'Horário Completo'
                    WHEN COALESCE(c.compatibilidade, 0) > 0 THEN 'Horário Parcial'
                    ELSE 'Horário Indisponível'
                END AS status_horario,
                COALESCE(
                    CASE 
                        WHEN preferencias.pref_count IS NOT NULL THEN 'Tem preferência'
                        ELSE 'Não tem preferência'
                    END, 'Não tem preferência'
                ) AS status_preferencia
            FROM professor p
            JOIN profissional prof ON p.id = prof.id
            LEFT JOIN Compatibilidade c ON p.id = c.professor_id
            LEFT JOIN (
                SELECT dp.professor_id, COUNT(DISTINCT hd.horario_aula_id) AS total_horarios
                FROM horario_disciplina hd
                JOIN disponibilidade_professor dp 
                    ON dp.horario_aula_id = hd.horario_aula_id
                    AND dp.dia_semana = hd.dia_semana
                WHERE hd.disciplina_id = :disciplinaId
                GROUP BY dp.professor_id
            ) AS horarios ON p.id = horarios.professor_id
            LEFT JOIN (
                SELECT ppd.prof_id, COUNT(ppd.disc_id) AS pref_count
                FROM preferencia_professor_disciplina ppd
                WHERE ppd.disc_id = :disciplinaId
                GROUP BY ppd.prof_id
            ) AS preferencias ON p.id = preferencias.prof_id
            WHERE p.id IN (
                SELECT prof_id FROM preferencia_professor_disciplina WHERE disc_id = :disciplinaId
                UNION
                SELECT dp.professor_id FROM disponibilidade_professor dp
                JOIN horario_disciplina hd ON dp.horario_aula_id = hd.horario_aula_id
                WHERE hd.disciplina_id = :disciplinaId
                UNION
                SELECT p.id FROM professor p
                JOIN professor_curso cp ON p.id = cp.professor_id
                WHERE cp.curso_id = (SELECT curso_id FROM disciplina WHERE id = :disciplinaId)
            )
            ORDER BY compatibilidade DESC;
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
                (Integer) row[4],
                (Integer) row[5],
                (Double) row[6],
                (String) row[7],
                (String) row[8]
            )
        ).toList();
    
        return dtos;
    }
    
}
