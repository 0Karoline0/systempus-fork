-- DROP TABLE IF EXISTS horario_aula_horarios;
-- DROP TABLE IF EXISTS horario_docente;
-- DROP TABLE IF EXISTS periodo_carga_horaria;
-- DROP TABLE IF EXISTS horario_disciplina;
-- DROP TABLE IF EXISTS disponibilidade_professor;
-- DROP TABLE IF EXISTS horario_aula;
-- DROP TABLE IF EXISTS professor_curso;
-- DROP TABLE IF EXISTS professor_disciplina;
-- DROP TABLE IF EXISTS curso_modulo;
-- DROP TABLE IF EXISTS coordenador_curso;
-- DROP TABLE IF EXISTS disciplina;
-- DROP TABLE IF EXISTS modulo;
-- DROP TABLE IF EXISTS professor;
-- DROP TABLE IF EXISTS carga_horaria;
-- DROP TABLE IF EXISTS periodo;
-- DROP TABLE IF EXISTS instituicao;
-- DROP TABLE IF EXISTS curso;
-- DROP TABLE IF EXISTS coordenador;
-- DROP TABLE IF EXISTS profissional;
-- DROP TABLE IF EXISTS teste;

-- CREATE TABLE teste (
--     id        serial,
--     nome        varchar(150)
-- );

-- CREATE TABLE instituicao (
--     id        serial                primary key,
--     nome      varchar(150),
--     cnpj      varchar(16)
-- );

-- CREATE TABLE profissional(
--     id serial primary key,
--     cpf varchar(11) not null,
--     nome varchar(100) not null,
--     telefone varchar(11) not null,
--     email varchar(30),
--     foto text,
--     status_profissional integer not null
-- );

-- CREATE TABLE professor(
--     id integer primary key,

--     constraint rel_professor_profissional foreign key(id) references profissional
-- );

-- CREATE TABLE coordenador(
--     id integer primary key,

--     constraint rel_coordenador_profissional foreign key (id) references profissional
-- );

-- CREATE TABLE curso(
--     id serial primary key,
--     nome varchar(150) not null,
--     nivel_Ensino integer not null,
--     modalidade integer not null,
--     qtd_Periodos integer not null,
--     carga_Total integer,
--     coordenador_id integer,

--     constraint rel_curso_coordenador foreign key(coordenador_id) references coordenador on delete set null
-- );

-- CREATE TABLE modulo(
--     id serial primary key,
--     nome varchar(60) not null,
--     data_Inicio date not null,
--     data_Fim date not null,
--     curso_id integer,

--     constraint rel_modulo_curso foreign key(curso_id) references curso on delete set null
-- );

-- CREATE TABLE disciplina(
--     id serial primary key,
--     nome varchar(100) not null,
--     modulo_id integer,

--     constraint rel_disciplina_modulo foreign key(modulo_id) references modulo on delete set null
-- );

-- CREATE TABLE professor_curso(
--     professor_id integer,
--     curso_id integer,

--     PRIMARY KEY (professor_id, curso_id),

--     constraint rel_professor_curso_curso foreign key (curso_id) references curso,
--     constraint rel_professor_curso_professor foreign key(professor_id) references professor
-- );

-- CREATE TABLE professor_disciplina(
--     professor_id integer,
--     disciplina_id integer,

--     PRIMARY KEY (professor_id, disciplina_id),

--     constraint rel_professor_disciplina_professor foreign key(professor_id) references professor on delete cascade,
--     constraint rel_professor_disciplina_disciplina foreign key(disciplina_id) references disciplina on delete cascade
-- );


-- CREATE TABLE carga_horaria (
--     id              serial          primary key,
--     carga_horaria   integer,
--     instituicao_id  integer,

--     constraint rel_carga_horaria_instituicao        foreign key(instituicao_id) references instituicao on delete cascade
-- );

-- CREATE TABLE periodo (
--     id                  serial          primary key,
--     turno               integer,
--     inicio_intervalo    TIME(60),
--     fim_intervalo       TIME(60),
--     inicio_horario      TIME(60),
--     fim_horario         TIME(60),
--     curso_id            integer,
--     instituicao_id      integer,
--     carga_horaria_id    integer,

--     constraint rel_periodo_curso              foreign key (curso_id)  references curso on delete cascade,
--     constraint rel_periodo_carga_horaria      foreign key (carga_horaria_id)  references carga_horaria on delete cascade,
--     constraint rel_periodo_instituicao        foreign key (instituicao_id)  references instituicao on delete cascade
-- );

-- CREATE TABLE horario_aula (
--     id                          serial      primary key,
--     carga_horaria_id            integer,
--     periodo_id                  integer,
--     inicio_aula                 TIME(60),
--     fim_aula                    TIME(60),

--     constraint rel_horario_aula_carga_horaria foreign key (carga_horaria_id) references carga_horaria on delete cascade,
--     constraint rel_horario_aula_carga_periodo foreign key (periodo_id) references periodo on delete cascade
-- );

-- CREATE TABLE disponibilidade_professor (
--     id                     serial      primary key,
--     horario_aula_id           integer,
--     professor_id              integer,
--     dia_semana                integer,

--     constraint rel_disponibilidade_professor_horario_aula foreign key (horario_aula_id) references horario_aula on delete cascade,
--     constraint rel_disponibilidade_professor_professor foreign key (professor_id) references professor on delete cascade
-- );

-- CREATE TABLE horario_disciplina (
--     id                  serial      primary key,
--     horario_aula_id     integer,
--     disciplina_id       integer,

--     constraint rel_horario_disciplina_horario_aula     foreign key (horario_aula_id) references horario_aula on delete cascade,
--     constraint rel_horario_disciplina_disciplina     foreign key (disciplina_id) references disciplina on delete cascade
-- );

-- -- CREATE TABLE horario_docente (
-- --     id                              serial      primary key,
-- --     horario_disciplina              integer,
-- --     disponibilidade_professor       integer,

-- --     constraint rel_horario_docente_disponibilidade_professor    foreign key (disponibilidade_professor) references disponibilidade_professor on delete cascade,
-- --     constraint rel_horario_docente_horario_disciplina       foreign key (horario_disciplina) references horario_disciplina on delete cascade
-- -- );

-- CREATE TABLE horario_docente (
--     id serial primary key,
    
--     horario_disciplina_horario_aula_id integer,
--     horario_disciplina_disciplina_id integer,

--     disponibilidade_professor_horario_aula_id integer,
--     disponibilidade_professor_professor_id integer,

--     CONSTRAINT fk_horario_disciplina_horario_aula FOREIGN KEY (horario_disciplina_horario_aula_id) REFERENCES horario_aula(id) ON DELETE CASCADE,
--     CONSTRAINT fk_horario_disciplina_disciplina FOREIGN KEY (horario_disciplina_disciplina_id) REFERENCES disciplina(id) ON DELETE CASCADE,

--     CONSTRAINT fk_disponibilidade_professor_horario_aula FOREIGN KEY (disponibilidade_professor_horario_aula_id) REFERENCES horario_aula(id) ON DELETE CASCADE,
--     CONSTRAINT fk_disponibilidade_professor_professor FOREIGN KEY (disponibilidade_professor_professor_id) REFERENCES professor(id) ON DELETE CASCADE
-- );