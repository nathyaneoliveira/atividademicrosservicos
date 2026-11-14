CREATE TABLE emprestimos (
    id CHAR(36) NOT NULL PRIMARY KEY,
    equipamento_id CHAR(36) NOT NULL,
    status VARCHAR(20) NOT NULL
);
