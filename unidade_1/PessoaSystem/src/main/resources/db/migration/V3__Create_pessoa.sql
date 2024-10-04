CREATE TABLE Pessoa (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    dataNascimento DATE NOT NULL,
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES Endereco(id) ON DELETE SET NULL
);