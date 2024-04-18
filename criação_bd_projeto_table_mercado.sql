CREATE DATABASE projeto;

CREATE TABLE IF NOT EXISTS mercado(
    id int auto_increment primary key,
    endereco varchar(255),
    nome_mercado varchar(255)
);