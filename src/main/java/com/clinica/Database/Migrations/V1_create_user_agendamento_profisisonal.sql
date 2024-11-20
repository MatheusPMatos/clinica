-- Criação do banco de dados
CREATE DATABASE clinicaBD;
USE clinicaBD;

-- Tabela User
CREATE TABLE User (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(14) NOT NULL UNIQUE
);

-- Tabela Profissional
CREATE TABLE Profissional (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(14) NOT NULL UNIQUE,
    categoria ENUM('Psicologia', 'Terapeuta Ocupacional', 'Pedagoga') -- Enum para categorias
);

-- Tabela Agendamento
CREATE TABLE Agendamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    data DATETIME NOT NULL,
    user_id BIGINT NOT NULL,
    profissional_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY (profissional_id) REFERENCES Profissional(id) ON DELETE CASCADE
);
