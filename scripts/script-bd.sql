-- #################################################
-- TABELA: USUARIOS (Entidade: Usuario)
-- #################################################
CREATE TABLE USUARIOS (
    -- BIGSERIAL p/ auto increment da PK
                          id BIGSERIAL PRIMARY KEY,

    -- Campos de Cadastro/Perfil
                          nome_completo VARCHAR(255),
                          cpf VARCHAR(14),
                          data_nascimento DATE,
                          cep VARCHAR(10),

    -- Campos de Login e Segurança
                          email VARCHAR(255) NOT NULL UNIQUE,
                          senha_criptografada VARCHAR(100) NOT NULL,

    -- Enum: Profissao
                          profissao VARCHAR(255)
);

-- #################################################
-- TABELA: PROGRESSO_USUARIO (Entidade: ProgressoUsuario)
-- #################################################
CREATE TABLE PROGRESSO_USUARIO (
                                   usuario_id BIGINT PRIMARY KEY, -- Chave Primária e Estrangeira

    -- Campos de Porcentagem
                                   percentual_geral_conclusao DOUBLE PRECISION DEFAULT 0.0,
                                   percentual_cursos_concluidos DOUBLE PRECISION DEFAULT 0.0,
                                   percentual_palestras_concluidas DOUBLE PRECISION DEFAULT 0.0,
                                   percentual_treinamentos_concluidos DOUBLE PRECISION DEFAULT 0.0,

                                   FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id)
                                       ON DELETE CASCADE
);

-- #################################################
-- TABELA: CURSOS (Entidade: Curso)
-- #################################################
CREATE TABLE CURSOS (
                        id BIGSERIAL PRIMARY KEY,
                        titulo VARCHAR(255),
                        descricao TEXT,
                        duracao_horas INTEGER,
                        instrutor VARCHAR(255),
                        is_tecnico BOOLEAN
);

-- #################################################
-- TABELA: PALESTRAS (Entidade: Palestra)
-- #################################################
CREATE TABLE PALESTRAS (
                           id BIGSERIAL PRIMARY KEY,
                           titulo VARCHAR(255),
                           palestrante VARCHAR(255),
                           tema_principal VARCHAR(255)
);

-- #################################################
-- TABELA: TREINAMENTOS (Entidade: Treinamento)
-- #################################################
CREATE TABLE TREINAMENTOS (
                              id BIGSERIAL PRIMARY KEY,
                              titulo VARCHAR(255),
                              foco_pratico VARCHAR(255),
                              duracao_minutos INTEGER
);

-- #################################################
-- TABELA DE COLEÇÃO: USUARIO_HABILIDADES
-- #################################################
CREATE TABLE USUARIO_HABILIDADES (
                                     usuario_id BIGINT NOT NULL,
                                     nome_habilidade VARCHAR(255) NOT NULL,

                                     PRIMARY KEY (usuario_id, nome_habilidade),
                                     FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id)
                                         ON DELETE CASCADE
);

-- #################################################
-- TABELA DE COLEÇÃO: USUARIO_OBJETIVOS
-- #################################################
CREATE TABLE USUARIO_OBJETIVOS (
                                   usuario_id BIGINT NOT NULL,
                                   nome_objetivo VARCHAR(255) NOT NULL,

                                   PRIMARY KEY (usuario_id, nome_objetivo),
                                   FOREIGN KEY (usuario_id) REFERENCES USUARIOS(id)
                                       ON DELETE CASCADE
);

-- #################################################
-- TABELAS DE JUNÇÃO para Progressos Concluídos
-- #################################################

-- Progresso - Cursos Concluídos
CREATE TABLE PROGRESSO_CURSOS_CONCLUIDOS (
                                             progresso_id BIGINT NOT NULL,
                                             curso_id BIGINT NOT NULL,

                                             PRIMARY KEY (progresso_id, curso_id),
                                             FOREIGN KEY (progresso_id) REFERENCES PROGRESSO_USUARIO(usuario_id) ON DELETE CASCADE,
                                             FOREIGN KEY (curso_id) REFERENCES CURSOS(id) ON DELETE CASCADE
);

-- Progresso - Palestras Concluídas
CREATE TABLE PROGRESSO_PALESTRAS_CONCLUIDAS (
                                                progresso_id BIGINT NOT NULL,
                                                palestra_id BIGINT NOT NULL,

                                                PRIMARY KEY (progresso_id, palestra_id),
                                                FOREIGN KEY (progresso_id) REFERENCES PROGRESSO_USUARIO(usuario_id) ON DELETE CASCADE,
                                                FOREIGN KEY (palestra_id) REFERENCES PALESTRAS(id) ON DELETE CASCADE
);

-- Progresso - Treinamentos Concluídos
CREATE TABLE PROGRESSO_TREINAMENTOS_CONCLUIDOS (
                                                   progresso_id BIGINT NOT NULL,
                                                   treinamento_id BIGINT NOT NULL,

                                                   PRIMARY KEY (progresso_id, treinamento_id),
                                                   FOREIGN KEY (progresso_id) REFERENCES PROGRESSO_USUARIO(usuario_id) ON DELETE CASCADE,
                                                   FOREIGN KEY (treinamento_id) REFERENCES TREINAMENTOS(id) ON DELETE CASCADE
);