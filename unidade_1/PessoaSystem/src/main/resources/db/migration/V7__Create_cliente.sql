CREATE TABLE Cliente (
    id UUID PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL,
    profissao_id UUID,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE,
    FOREIGN KEY (profissao_id) REFERENCES Profissao(id) ON DELETE SET NULL
);