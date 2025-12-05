-- ALTER SEQUENCE profissional_id_seq RESTART WITH 1;
-- ALTER SEQUENCE curso_id_seq RESTART WITH 1;
-- ALTER SEQUENCE modulo_id_seq RESTART WITH 1;
-- ALTER SEQUENCE disciplina_id_seq RESTART WITH 1;
-- ALTER SEQUENCE instituicao_id_seq RESTART WITH 1;
-- ALTER SEQUENCE carga_horaria_id_seq RESTART WITH 1;
-- ALTER SEQUENCE periodo_id_seq RESTART WITH 1;
-- ALTER SEQUENCE horario_aula_id_seq RESTART WITH 1;
-- ALTER SEQUENCE disponibilidade_professor_id_seq RESTART WITH 1;
-- ALTER SEQUENCE professor_disciplina_id_seq RESTART WITH 1;

-- DELETE FROM professor_disciplina;
-- DELETE FROM disponibilidade_professor;
-- DELETE FROM horario_disciplina;
-- DELETE FROM horario_aula;
-- DELETE FROM professor_curso;
-- DELETE FROM professor_disciplina;
-- DELETE FROM disciplina;
-- DELETE FROM modulo;
-- DELETE FROM professor;
-- DELETE FROM carga_horaria;
-- DELETE FROM periodo;
-- DELETE FROM instituicao;
-- DELETE FROM curso;
-- DELETE FROM coordenador;
-- DELETE FROM profissional;

-- -- ALTER TABLE disponibilidade_professor DROP CONSTRAINT uk_g4j2flr6lb67dd2w89laprade;











DELETE FROM profissional_roles;
DELETE FROM profissional_role;
DELETE FROM profissional;
DELETE FROM professor;
DELETE FROM pessoa;

INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('12345678909', 'Karol Souza', '11999990001', 'karol@gmail.com', 'https://img.cancaonova.com/cnimages/canais/uploads/sites/6/2018/03/formacao_1600x1200-como-a-presenca-da-mulher-pode-ser-harmonia-no-mundo.jpg', 1);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('98765432100', 'Roussian Gaioso', '21988887777', 'roussian@gmail.com', 'https://img.freepik.com/fotos-gratis/homem-bonito-posando-e-sorrindo_23-2149396133.jpg?w=360', 1);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('11122233344', 'Karoline Vitória', '31977776666', 'karolinevmfaria@gmail.com', 'https://t4.ftcdn.net/jpg/05/54/32/25/360_F_554322527_L4qTbf9iGZFdxaokxfm6KoQClwfmUBSq.jpg', 1);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('55566677788', 'Roger Leo', '41966665555', 'rogerleo@gmail.com', 'diego.jpg', 1);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('99988877766', 'Eduarda Martins', '51955554444', 'eduarda@gmail.com', 'eduarda.png', 1);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('29803896091', 'Erick Lima Cavalcanti', '97922208738', 'teste@gmail.com', 'https://img.freepik.com/fotos-gratis/retrato-de-homem-feliz-e-sorridente_23-2149022620.jpg', 0);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('70622239058', 'Luís Pereira Santos', '6836364963', 'luis@outlook.com', NULL, 0);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('98579973090', 'Laura Cavalcanti Melo', '69926295047', 'laura@yahoo.com.br', 'https://img.freepik.com/fotos-gratis/retrato-de-uma-jovem-linda-modelo-de-pe-e-sorrindo-para-a-camera-foto-de-alta-qualidade_144627-75055.jpg', 1);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('77230341051', 'Amanda Gomes Cardoso', '63932156476', 'amandaGomes@gmail.com.br', NULL, 1);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('72387595009', 'Larissa Rodrigues Pinto', '9325217455', 'teste1@gmail.com.br', NULL, 2);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('54018147040', 'Julian Fernandes Gomes', '8137598566', 'teste2@gmail.com', 'https://img.freepik.com/fotos-gratis/homem-bonito-posando-e-sorrindo_23-2149396133.jpg', 2);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('93363186088', 'Manuela Martins Lima', '63939481714', 'teste3@gmail.com', 'https://midias.correiobraziliense.com.br/_midias/jpg/2021/03/05/675x450/1_cbpfot020320212188-6556336.jpg', 2);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('71935254090', 'Mariana Barros Cunha', '1722816185', NULL, 'https://media.istockphoto.com/id/1961055517/pt/foto/testimonial-portrait-of-a-mature-mexican-woman.webp?b=1&s=170667a&w=0&k=20&c=hrlMKXux9zL82L92rgUWN4ARrL2Cgu-PRLK0iAY_MVk=', 0);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('88411047083', 'Matheus Rocha Sousa', '94938884730', NULL, NULL, 0);
INSERT INTO pessoa (cpf, nome, telefone, email, foto, status_ativacao) VALUES ('22525229096', 'Kauan Souza Fernandes', '33922412233', NULL, 'https://media.istockphoto.com/id/1338134336/pt/foto/headshot-portrait-african-30s-man-smile-look-at-camera.webp?b=1&s=170667a&w=0&k=20&c=SFQyWuhNqsI4YypI1rQEwV3yv9RMzkHoXaHrz_Jkft8=', 2);

INSERT INTO profissional (id) VALUES (1); -- KAROL SOUZA
INSERT INTO profissional (id) VALUES (2); -- ROUSSIAN
INSERT INTO profissional (id) VALUES (3); -- KAROLINE VITÓRIA
INSERT INTO profissional (id) VALUES (4); -- ROGER LEO
INSERT INTO profissional (id) VALUES (5); -- EDUARDA
INSERT INTO profissional (id) VALUES (6);
INSERT INTO profissional (id) VALUES (7);
INSERT INTO profissional (id) VALUES (8);
INSERT INTO profissional (id) VALUES (9);
INSERT INTO profissional (id) VALUES (10);

INSERT INTO profissional_role (tipo_profissional, profissional_id) VALUES ('PROFESSOR', 1); -- KAROL SOUZA (PROFESSOR)
INSERT INTO profissional_role (tipo_profissional, profissional_id) VALUES ('COORDENADOR', 1); -- KAROL SOUZA (COORDENADOR)
INSERT INTO profissional_role (tipo_profissional, profissional_id) VALUES ('COORDENADOR', 2); -- ROUSSIAN (COORDENADOR)
INSERT INTO profissional_role (tipo_profissional, profissional_id) VALUES ('PROFESSOR', 3); -- KAROLINE VITÓRIA (PROFESSOR)
INSERT INTO profissional_role (tipo_profissional, profissional_id) VALUES ('ADM', 4); -- ROGER LEO (ADMN)
INSERT INTO profissional_role (tipo_profissional, profissional_id) VALUES ('PROFESSOR', 5); -- EDUARDA (PROFESSOR)

INSERT INTO professor (id) VALUES (1);
INSERT INTO professor (id) VALUES (4);
INSERT INTO professor (id) VALUES (6);

INSERT INTO coordenador (id) VALUES (2);
INSERT INTO coordenador (id) VALUES (3);

INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (500, 0, 4, 'Análise e Desenvolvimento de Sistemas', 5, 2);
INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (400, 1, 4, 'Design Gráfico', 4, 2);
INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (350, 2, 4, 'Administrador de Banco de Dados', 8, 2);
INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (400, 2, 4, 'Programador de Sistemas', 8, 2);
INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (400, 2, 4, 'Desenvolvedor Web', 8, 2);

INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('AB-1', '2023-02-01', '2023-06-15', 1);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('AB-2', '2023-08-01', '2023-12-10', 1);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('CA-1', '2023-02-01', '2023-06-12', 2);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('BE-2', '2023-08-01', '2023-12-15', 2);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('AV-2', '2023-02-01', '2023-06-12', 3);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('CA-2', '2023-08-01', '2023-12-10', 3);

INSERT INTO disciplina(nome, modulo_id, quantidade_cargas) VALUES ('Fundamentos de Redes', 1, 2);
INSERT INTO disciplina(nome, modulo_id, quantidade_cargas) VALUES ('Programação Orientada a Objetos', 2, 8);
INSERT INTO disciplina(nome, modulo_id, quantidade_cargas) VALUES ('Engenharia de Software', 1, 2);
INSERT INTO disciplina(nome, modulo_id, quantidade_cargas) VALUES ('Fundamentos de Banco de Dados', 2, 8);
INSERT INTO disciplina(nome, modulo_id, quantidade_cargas) VALUES ('Fundamento das Cores', 3, 4);
INSERT INTO disciplina(nome, modulo_id, quantidade_cargas) VALUES ('Arte Pré-Histórica', 4, 2);

INSERT INTO professor_curso(professor_id, curso_id) VALUES (1, 1);
INSERT INTO professor_curso(professor_id, curso_id) VALUES (1, 2);
INSERT INTO professor_curso(professor_id, curso_id) VALUES (1, 3);
INSERT INTO professor_curso(professor_id, curso_id) VALUES (1, 4);
INSERT INTO professor_curso(professor_id, curso_id) VALUES (1, 5);

INSERT INTO instituicao(nome, cnpj) VALUES ('Faculdade Teste', '321.323.0001/232');
INSERT INTO instituicao(nome, cnpj) VALUES ('Faculdade Senac', '222.444.0001/232');
INSERT INTO instituicao(nome, cnpj) VALUES ('Faculdade Goiás', '662.111.0001/232');

INSERT INTO carga_horaria(carga_horaria, instituicao_id) VALUES (80, 1);
INSERT INTO carga_horaria(carga_horaria, instituicao_id) VALUES (25, 2);
INSERT INTO carga_horaria(carga_horaria, instituicao_id) VALUES (50, 1);

INSERT INTO periodo (curso_id, turno, instituicao_id, inicio_intervalo, fim_intervalo, inicio_horario, fim_horario, carga_horaria_id) VALUES (1, 0, 1, '09:50', '10:10', '08:10', '11:40', 3);
INSERT INTO periodo (curso_id, turno, instituicao_id, inicio_intervalo, fim_intervalo, inicio_horario, fim_horario, carga_horaria_id) VALUES (1, 2, 1, '19:50', '20:10', '18:30', '22:00', 3);

INSERT INTO professor_disciplina(professor_id, disciplina_id, status_disciplina_professor) VALUES (1, 4, 0);
INSERT INTO professor_disciplina(professor_id, disciplina_id, status_disciplina_professor) VALUES (4, 1, 0);
INSERT INTO professor_disciplina(professor_id, disciplina_id, status_disciplina_professor) VALUES (1, 2, 0);
INSERT INTO professor_disciplina(professor_id, disciplina_id, status_disciplina_professor) VALUES (4, 3, 0);
INSERT INTO professor_disciplina(professor_id, disciplina_id, status_disciplina_professor) VALUES (1, 6, 0);

INSERT INTO preferencia_professor_disciplina (prof_id, disc_id) VALUES (1, 1);
INSERT INTO preferencia_professor_disciplina (prof_id, disc_id) VALUES (4, 3);
INSERT INTO preferencia_professor_disciplina (prof_id, disc_id) VALUES (4, 5);
INSERT INTO preferencia_professor_disciplina (prof_id, disc_id) VALUES (4, 6);

INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 1, '08:10', '09:00');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 1, '09:00', '09:50');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 1, '10:00', '10:50');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 1, '10:50', '11:40');

INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 2, '18:30', '19:20');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 2, '19:20', '20:10');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 2, '20:20', '21:10');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 2, '21:10', '22:00');

INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (0, 1, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (0, 2, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (0, 3, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (0, 4, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (1, 1, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (1, 2, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (1, 3, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (2, 5, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (2, 6, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (5, 7, 1);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (5, 8, 1);

INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (2, 5, 4);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (2, 6, 4);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (5, 1, 4);
INSERT INTO disponibilidade_professor(dia_semana, horario_aula_id, professor_id) VALUES (5, 2, 4);

INSERT INTO horario_disciplina(dia_semana, horario_aula_id, disciplina_id) VALUES (0, 1, 1);
INSERT INTO horario_disciplina(dia_semana, horario_aula_id, disciplina_id) VALUES (0, 2, 1);
INSERT INTO horario_disciplina(dia_semana, horario_aula_id, disciplina_id) VALUES (0, 3, 1);
INSERT INTO horario_disciplina(dia_semana, horario_aula_id, disciplina_id) VALUES (0, 4, 1);
INSERT INTO horario_disciplina(dia_semana, horario_aula_id, disciplina_id) VALUES (6, 1, 3);
INSERT INTO horario_disciplina(dia_semana, horario_aula_id, disciplina_id) VALUES (6, 2, 3);

INSERT INTO usuario(username, password, profissional_id) VALUES ('karolinevmfaria@gmail.com', '$2a$12$Xh4PvgfqDnviETd27ac0IeZqG79/Lntb8XRqUPVHTYN3A5ySzkw5m', 3);
INSERT INTO usuario(username, password, profissional_id) VALUES ('karol@gmail.com', '$2a$12$Xh4PvgfqDnviETd27ac0IeZqG79/Lntb8XRqUPVHTYN3A5ySzkw5m', 1);
INSERT INTO usuario(username, password, profissional_id) VALUES ('roussian@gmail.com', '$2a$12$Xh4PvgfqDnviETd27ac0IeZqG79/Lntb8XRqUPVHTYN3A5ySzkw5m', 2);
INSERT INTO usuario(username, password, profissional_id) VALUES ('rogerleo@gmail.com', '$2a$12$Xh4PvgfqDnviETd27ac0IeZqG79/Lntb8XRqUPVHTYN3A5ySzkw5m', 4);
INSERT INTO usuario(username, password, profissional_id) VALUES ('eduarda@gmail.com', '$2a$12$Xh4PvgfqDnviETd27ac0IeZqG79/Lntb8XRqUPVHTYN3A5ySzkw5m', 5);




INSERT INTO permissoes(id, nome) VALUES (1, 'Listar Professor');
INSERT INTO permissoes(id, nome) VALUES (2, 'Cadastrar Professor');
INSERT INTO permissoes(id, nome) VALUES (3, 'Editar Professor');
INSERT INTO permissoes(id, nome) VALUES (4, 'Excluir Professor');
INSERT INTO permissoes(id, nome) VALUES (5, 'Editar Horário Professor');

INSERT INTO permissoes(id, nome) VALUES (6, 'Listar Coordenador');
INSERT INTO permissoes(id, nome) VALUES (7, 'Cadastrar Coordenador');
INSERT INTO permissoes(id, nome) VALUES (8, 'Editar Coordenador');
INSERT INTO permissoes(id, nome) VALUES (9, 'Excluir Coordenador');
INSERT INTO permissoes(id, nome) VALUES (10, 'Editar Horário Coordenador');

INSERT INTO permissoes(id, nome) VALUES (11, 'Listar Curso');
INSERT INTO permissoes(id, nome) VALUES (12, 'Cadastrar Curso');
INSERT INTO permissoes(id, nome) VALUES (13, 'Editar Curso');
INSERT INTO permissoes(id, nome) VALUES (14, 'Excluir Curso');

INSERT INTO permissoes(id, nome) VALUES (15, 'Listar Período');
INSERT INTO permissoes(id, nome) VALUES (16, 'Cadastrar Período');
INSERT INTO permissoes(id, nome) VALUES (17, 'Editar Período');
INSERT INTO permissoes(id, nome) VALUES (18, 'Excluir Período');

INSERT INTO permissoes(id, nome) VALUES (19, 'Listar Módulo');
INSERT INTO permissoes(id, nome) VALUES (20, 'Cadastrar Módulo');
INSERT INTO permissoes(id, nome) VALUES (21, 'Editar Módulo');
INSERT INTO permissoes(id, nome) VALUES (22, 'Excluir Módulo');

INSERT INTO permissoes(id, nome) VALUES (23, 'Listar Disciplina');
INSERT INTO permissoes(id, nome) VALUES (24, 'Cadastrar Disciplina');
INSERT INTO permissoes(id, nome) VALUES (25, 'Editar Disciplina');
INSERT INTO permissoes(id, nome) VALUES (26, 'Excluir Disciplina');
INSERT INTO permissoes(id, nome) VALUES (27, 'Editar Horário Disciplina');

INSERT INTO permissoes(id, nome) VALUES (28, 'Listar Horário Docente');
INSERT INTO permissoes(id, nome) VALUES (29, 'Cadastrar Horário Docente');
INSERT INTO permissoes(id, nome) VALUES (30, 'Editar Horário Docente');

INSERT INTO permissoes(id, nome) VALUES (31, 'Favoritar Disciplina');
INSERT INTO permissoes(id, nome) VALUES (32, 'Enviar Mensagem');

INSERT INTO permissoes(id, nome) VALUES (33, 'Acessa Menu Curso');
INSERT INTO permissoes(id, nome) VALUES (34, 'Acessa Menu Disciplina');
INSERT INTO permissoes(id, nome) VALUES (35, 'Acessa Menu Coordenador');
INSERT INTO permissoes(id, nome) VALUES (36, 'Acessa Menu Professor');
INSERT INTO permissoes(id, nome) VALUES (37, 'Acessa Menu Horário Docente');

INSERT INTO permissoes(id, nome) VALUES (38, 'Alterar Status Coordenador');
INSERT INTO permissoes(id, nome) VALUES (39, 'Alterar Status Professor');
INSERT INTO permissoes(id, nome) VALUES (40, 'Reenviar Email Cadastro');

INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (1, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (2, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (3, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (4, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (5, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (6, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (7, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (8, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (9, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (10, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (11, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (12, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (13, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (14, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (15, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (16, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (17, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (18, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (19, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (20, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (21, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (22, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (23, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (24, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (25, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (26, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (27, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (28, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (29, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (30, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (31, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (32, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (33, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (34, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (35, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (36, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (37, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (38, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (39, 'COORDENADOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (40, 'COORDENADOR');

INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (30, 'PROFESSOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (31, 'PROFESSOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (33, 'PROFESSOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (11, 'PROFESSOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (23, 'PROFESSOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (34, 'PROFESSOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (37, 'PROFESSOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (19, 'PROFESSOR');
INSERT INTO permissoes_roles(permissoes_id, roles) VALUES (15, 'PROFESSOR');

INSERT INTO usuario_role(usuario_id, role) VALUES (1, 'COORDENADOR');
INSERT INTO usuario_role(usuario_id, role) VALUES (2, 'PROFESSOR');