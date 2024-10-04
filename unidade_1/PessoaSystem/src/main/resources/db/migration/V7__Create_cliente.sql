CREATE TABLE Cliente (
    id INT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL,
    profissao_id INT,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE,
    FOREIGN KEY (profissao_id) REFERENCES Profissao(id) ON DELETE SET NULL
);