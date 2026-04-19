-- Tabela de usuários (PostgreSQL / Cloud SQL)
CREATE TABLE IF NOT EXISTS users (
    nome_user    TEXT NOT NULL,
    email        TEXT PRIMARY KEY,
    senha        TEXT NOT NULL,
    indicado_por TEXT NULL
);
