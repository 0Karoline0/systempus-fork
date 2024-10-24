INSERT INTO profissional(cpf, nome, status_profissional, telefone, email, foto) VALUES ('29803896091', 'Erick Lima Cavalcanti', 1, '97922208738', 'erick@outlook.com', 'https://img.freepik.com/fotos-gratis/retrato-de-homem-feliz-e-sorridente_23-2149022620.jpg');
INSERT INTO profissional(cpf, nome, status_profissional, telefone, email) VALUES ('70622239058', 'Luís Pereira Santos', 2, '6836364963', 'luis@outlook.com');
INSERT INTO profissional(cpf, nome, status_profissional, telefone, email, foto) VALUES ('98579973090', 'Laura Cavalcanti Melo', 2, '69926295047', 'laura@yahoo.com.br', 'https://img.freepik.com/fotos-gratis/retrato-de-uma-jovem-linda-modelo-de-pe-e-sorrindo-para-a-camera-foto-de-alta-qualidade_144627-75055.jpg');
INSERT INTO profissional(cpf, nome, status_profissional, telefone, email) VALUES ('77230341051', 'Amanda Gomes Cardoso', 0, '63932156476', 'amandaGomes@gmail.com.br');
INSERT INTO profissional(cpf, nome, status_profissional, telefone, email) VALUES ('72387595009', 'Larissa Rodrigues Pinto', 1, '9325217455', 'larissaRodrigues@gmail.com.br');
INSERT INTO profissional(cpf, nome, status_profissional, telefone, foto) VALUES ('54018147040', 'Julian Fernandes Gomes', 0, '8137598566', 'https://img.freepik.com/fotos-gratis/homem-bonito-posando-e-sorrindo_23-2149396133.jpg');
INSERT INTO profissional(cpf, nome, status_profissional, telefone, foto) VALUES ('93363186088', 'Manuela Martins Lima', 0, '63939481714', 'https://midias.correiobraziliense.com.br/_midias/jpg/2021/03/05/675x450/1_cbpfot020320212188-6556336.jpg');
INSERT INTO profissional(cpf, nome, status_profissional, telefone, foto) VALUES ('71935254090', 'Mariana Barros Cunha', 0, '1722816185', 'https://media.istockphoto.com/id/1961055517/pt/foto/testimonial-portrait-of-a-mature-mexican-woman.webp?b=1&s=170667a&w=0&k=20&c=hrlMKXux9zL82L92rgUWN4ARrL2Cgu-PRLK0iAY_MVk=');
INSERT INTO profissional(cpf, nome, status_profissional, telefone) VALUES ('88411047083', 'Matheus Rocha Sousa', 0, '94938884730');
INSERT INTO profissional(cpf, nome, status_profissional, telefone, foto) VALUES ('22525229096', 'Kauan Souza Fernandes', 0, '33922412233', 'https://media.istockphoto.com/id/1338134336/pt/foto/headshot-portrait-african-30s-man-smile-look-at-camera.webp?b=1&s=170667a&w=0&k=20&c=SFQyWuhNqsI4YypI1rQEwV3yv9RMzkHoXaHrz_Jkft8=');

INSERT INTO coordenador(id) VALUES(4);
INSERT INTO coordenador(id) VALUES(5);
INSERT INTO coordenador(id) VALUES(9);

INSERT INTO professor(id) VALUES(1);
INSERT INTO professor(id) VALUES(2);
INSERT INTO professor(id) VALUES(3);
INSERT INTO professor(id) VALUES(6);
INSERT INTO professor(id) VALUES(7);
INSERT INTO professor(id) VALUES(8);
INSERT INTO professor(id) VALUES(10);

INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (500, 0, 4, 'Análise e Desenvolvimento de Sistemas', 5, 4);
INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (400, 1, 4, 'Design Gráfico', 4, 5);
INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (350, 2, 4, 'Administrador de Banco de Dados', 8, 9);
INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (400, 2, 4, 'Programador de Sistemas', 8, 9);
INSERT INTO curso(carga_Total, modalidade, nivel_Ensino, nome, qtd_Periodos, coordenador_id) VALUES (400, 2, 4, 'Desenvolvedor Web', 8, 4);

INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('AB-1', '2023-02-01', '2023-06-15', 1);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('AB-2', '2023-08-01', '2023-12-10', 1);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('CA-1', '2023-02-01', '2023-06-12', 2);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('BE-2', '2023-08-01', '2023-12-15', 2);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('AV-2', '2023-02-01', '2023-06-12', 3);
INSERT INTO modulo(nome, data_Inicio, data_Fim, curso_id) VALUES ('CA-2', '2023-08-01', '2023-12-10', 3);

INSERT INTO disciplina(nome, modulo_id) VALUES ('Fundamentos de Redes', 1);
INSERT INTO disciplina(nome, modulo_id) VALUES ('Programação Orientada a Objetos', 2);
INSERT INTO disciplina(nome, modulo_id) VALUES ('Engenharia de Software', 1);
INSERT INTO disciplina(nome, modulo_id) VALUES ('Fundamentos de Banco de Dados', 2);
INSERT INTO disciplina(nome, modulo_id) VALUES ('Fundamento das Cores', 3);
INSERT INTO disciplina(nome, modulo_id) VALUES ('Arte Pré-Histórica', 4);

INSERT INTO professor_curso(professor_id, curso_id) VALUES (1, 1);
INSERT INTO professor_curso(professor_id, curso_id) VALUES (2, 1);
INSERT INTO professor_curso(professor_id, curso_id) VALUES (3, 1);
INSERT INTO professor_curso(professor_id, curso_id) VALUES (6, 2);

INSERT INTO professor_disciplina(professor_id, disciplina_id) VALUES (1, 4);
INSERT INTO professor_disciplina(professor_id, disciplina_id) VALUES (3, 1);
INSERT INTO professor_disciplina(professor_id, disciplina_id) VALUES (1, 2);
INSERT INTO professor_disciplina(professor_id, disciplina_id) VALUES (3, 3);
INSERT INTO professor_disciplina(professor_id, disciplina_id) VALUES (6, 6);

INSERT INTO instituicao(nome, cnpj) VALUES ('Faculdade Teste', '321.323.0001/232');
INSERT INTO instituicao(nome, cnpj) VALUES ('Faculdade Senac', '222.444.0001/232');
INSERT INTO instituicao(nome, cnpj) VALUES ('Faculdade Goiás', '662.111.0001/232');

INSERT INTO carga_horaria(carga_horaria, instituicao_id) VALUES (80, 1);
INSERT INTO carga_horaria(carga_horaria, instituicao_id) VALUES (25, 2);
INSERT INTO carga_horaria(carga_horaria, instituicao_id) VALUES (50, 1);

INSERT INTO periodo (curso_id, turno, instituicao_id, inicio_intervalo, fim_intervalo, inicio_horario, fim_horario, carga_horaria) VALUES (1, 0, 1, '09:50', '10:10', '08:10', '11:40', 3);
INSERT INTO periodo (curso_id, turno, instituicao_id, inicio_intervalo, fim_intervalo, inicio_horario, fim_horario, carga_horaria) VALUES (1, 2, 1, '19:50', '20:10', '18:30', '22:00', 3);

INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 1, '08:10', '09:00');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 1, '09:00', '09:50');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 1, '10:00', '10:50');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 1, '10:50', '11:40');

INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 2, '18:30', '19:20');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 2, '19:20', '20:10');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 2, '20:20', '21:10');
INSERT INTO horario_aula(carga_horaria_id, periodo_id, inicio_aula, fim_aula) VALUES (3, 2, '21:10', '22:00');

INSERT INTO horario_disciplina(horario_aula_id, disciplina_id) VALUES (1, 1);