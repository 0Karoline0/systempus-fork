WITH Compatibilidade AS (
    SELECT 
        COALESCE(horarios.professor_id, preferencias.prof_id) AS professor_id, 
        (COALESCE(horarios.compatibilidade, 0) * 10) + 
        COALESCE(preferencias.pref_count, 0) AS compatibilidade,
        COALESCE(horarios.compatibilidade, 0) AS horarios_compativeis,
        -- Adicionando a verificação de preferência
        CASE 
            WHEN preferencias.prof_id IS NOT NULL THEN 'Tem preferência'
            ELSE 'Não tem preferência'
        END AS status_preferencia
    FROM (
        -- Contagem de horários compatíveis
        SELECT 
            dp.professor_id, 
            COUNT(dp.horario_aula_id) AS compatibilidade
        FROM disponibilidade_professor dp
        JOIN horario_disciplina hd 
            ON dp.horario_aula_id = hd.horario_aula_id
            AND dp.dia_semana = hd.dia_semana  
        WHERE hd.disciplina_id = 1
        GROUP BY dp.professor_id
    ) AS horarios
    FULL OUTER JOIN (
        -- Contagem de preferências por disciplina
        SELECT 
            ppd.prof_id, 
            COUNT(ppd.disc_id) AS pref_count
        FROM preferencia_professor_disciplina ppd
        WHERE ppd.disc_id = 1
        GROUP BY ppd.prof_id
    ) AS preferencias 
    ON horarios.professor_id = preferencias.prof_id
)

SELECT 
    p.id,
    COALESCE(c.compatibilidade, 0) AS compatibilidade,
    ROUND((COALESCE(c.compatibilidade, 0) * 100.0) / MAX(c.compatibilidade) OVER(), 2) AS compatibilidade_percent,
    CASE 
        WHEN c.horarios_compativeis > 0 AND c.horarios_compativeis = (SELECT COUNT(*) FROM horario_disciplina WHERE disciplina_id = 1) THEN 'Horário Completo'
        WHEN c.horarios_compativeis > 0 AND c.horarios_compativeis < (SELECT COUNT(*) FROM horario_disciplina WHERE disciplina_id = 1) THEN 'Horário Parcial'
        ELSE 'Horário Indisponível'
    END AS status_horario,
    COALESCE(c.status_preferencia, 'Não tem preferência') AS status_preferencia
FROM professor p
LEFT JOIN Compatibilidade c 
    ON p.id = c.professor_id
WHERE p.id IN (
    -- Inclui todos os professores relacionados ao curso da disciplina, independentemente de terem horário ou não
    SELECT prof_id
    FROM preferencia_professor_disciplina
    WHERE disc_id = 1
    UNION
    SELECT dp.professor_id
    FROM disponibilidade_professor dp
    JOIN horario_disciplina hd 
        ON dp.horario_aula_id = hd.horario_aula_id
    WHERE hd.disciplina_id = 1
    UNION
    -- Selecionando todos os professores do curso
    SELECT p.id
    FROM professor p
    JOIN professor_curso cp ON p.id = cp.professor_id
    WHERE cp.curso_id = (SELECT curso_id FROM disciplina WHERE id = 1)
)
ORDER BY compatibilidade DESC;
