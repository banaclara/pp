CREATE DATABASE ppunidadeI;

USE ppunidadeI;

CREATE TABLE Endereco (
    id SERIAL PRIMARY KEY,
    pais VARCHAR(100) NOT NULL,
    estado VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(100)
);

CREATE TABLE Profissao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES Endereco(id) ON DELETE SET NULL
);

CREATE TABLE Telefone (
    id SERIAL PRIMARY KEY,
    ddd VARCHAR(3) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    pessoa_id INT,
    FOREIGN KEY (pessoa_id) REFERENCES Pessoa(id) ON DELETE CASCADE
);

CREATE TYPE cargo_enum AS ENUM (
    'GERENTE',
    'ANALISTA',
    'DESENVOLVEDOR',
    'DEVOPS',
    'DESIGNER',
    'SUPORTE',
    'QA',
    'ESTAGIARIO'
);

CREATE TABLE Funcionario (
    id INT PRIMARY KEY,
    matricula VARCHAR(15) NOT NULL,
    cargo cargo_enum NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    dataAdmissao DATE NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE
);

CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL,
    profissao_id INT,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE,
    FOREIGN KEY (profissao_id) REFERENCES Profissao(id) ON DELETE SET NULL
);
