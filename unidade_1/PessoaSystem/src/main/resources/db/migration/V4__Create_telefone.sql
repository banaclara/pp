CREATE TABLE Telefone (
    id SERIAL PRIMARY KEY,
    ddd VARCHAR(3) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    pessoa_id INT,
    FOREIGN KEY (pessoa_id) REFERENCES Pessoa(id) ON DELETE CASCADE
);