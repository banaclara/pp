CREATE TABLE Telefone (
    id UUID PRIMARY KEY,
    ddd VARCHAR(3) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    pessoa_id UUID,
    FOREIGN KEY (pessoa_id) REFERENCES Pessoa(id) ON DELETE CASCADE
);