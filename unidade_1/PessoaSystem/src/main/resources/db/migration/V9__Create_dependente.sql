CREATE TABLE Dependente (
    id UUID PRIMARY KEY,
    parentesco parentesco_enum NOT NULL,
    cliente_parente UUID,
    funcionario_parente UUID,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE,
    FOREIGN KEY (cliente_parente) REFERENCES Cliente(id) ON DELETE CASCADE,
    FOREIGN KEY (funcionario_parente) REFERENCES Funcionario(id) ON DELETE CASCADE,
    CHECK (
        (cliente_parente IS NOT NULL AND funcionario_parente IS NULL) OR
        (cliente_parente IS NULL AND funcionario_parente IS NOT NULL)
        )
);