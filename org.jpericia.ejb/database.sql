-- Sequence para a tabela: FuncaoPerito
CREATE SEQUENCE funcao_perito_seq START 1;

-- Tabela: FuncaoPerito
CREATE TABLE funcao_perito (
    codigo       integer PRIMARY KEY,
    funcao       varchar(50) NOT NULL
);

-- Sequence para a tabela: uf
CREATE SEQUENCE uf_seq START 1;

-- Tabela UF
CREATE TABLE uf (
    codigo       integer PRIMARY KEY,
    uf       varchar(50) NOT NULL
);