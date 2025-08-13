-- Tabela de Usuários
CREATE TABLE usuarios (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          nome VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          senha VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id)
);

-- Tabela de Cursos
CREATE TABLE cursos (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        nome VARCHAR(100) NOT NULL UNIQUE,
                        categoria VARCHAR(100) NOT NULL,
                        PRIMARY KEY (id)
);

-- Tabela de Tópicos
CREATE TABLE topicos (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         titulo VARCHAR(255) NOT NULL UNIQUE,
                         mensagem TEXT NOT NULL,
                         data_criacao DATETIME NOT NULL,
                         status VARCHAR(50) NOT NULL,
                         autor_id BIGINT NOT NULL,
                         curso_id BIGINT NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (autor_id) REFERENCES usuarios(id),
                         FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

-- Tabela de Respostas
CREATE TABLE respostas (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           mensagem TEXT NOT NULL,
                           data_criacao DATETIME NOT NULL,
                           solucao BOOLEAN DEFAULT FALSE,
                           autor_id BIGINT NOT NULL,
                           topico_id BIGINT NOT NULL,
                           PRIMARY KEY (id),
                           FOREIGN KEY (autor_id) REFERENCES usuarios(id),
                           FOREIGN KEY (topico_id) REFERENCES topicos(id)
);