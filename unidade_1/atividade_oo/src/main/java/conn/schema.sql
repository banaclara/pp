CREATE DATABASE ppunidadeI;

USE ppunidadeI;

CREATE TABLE Endereco (
    id INT IDENTITY PRIMARY KEY,
    estado VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    complemento VARCHAR(100) DEFAULT NULL
);

CREATE TABLE Cargo (
    id INT IDENTITY PRIMARY KEY,
    funcao VARCHAR(100) NOT NULL
);

CREATE TABLE Profissao (
    id INT IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE Pessoa (
    id INT IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES Endereco(id) ON DELETE SET NULL
);

CREATE TABLE Telefone (
    id INT IDENTITY PRIMARY KEY,
    ddd VARCHAR(2) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    pessoa_id INT,
    FOREIGN KEY (pessoa_id) REFERENCES Pessoa(id) ON DELETE CASCADE
);

CREATE TABLE Funcionario (
    id INT PRIMARY KEY,
    matricula VARCHAR(15) NOT NULL,
    cargo_id INT,
    salario DECIMAL(10, 2) NOT NULL,
    dataAdmissao DATE NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE,
    FOREIGN KEY (cargo_id) REFERENCES Cargo(id) ON DELETE SET NULL
);

CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL,
    profissao_id INT,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE,
    FOREIGN KEY (profissao_id) REFERENCES Profissao(id) ON DELETE SET NULL
);
