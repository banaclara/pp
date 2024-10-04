CREATE TABLE Funcionario (
    id INT PRIMARY KEY,
    matricula VARCHAR(15) NOT NULL,
    cargo cargo_enum NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    dataAdmissao DATE NOT NULL,
    FOREIGN KEY (id) REFERENCES Pessoa(id) ON DELETE CASCADE
);