create database estoque;

create table produtos(
	id int PRIMARY KEY auto_increment,
    nome varchar(255),
    quantidade int,
    preco_unitario decimal(5,2),
    preco_total decimal(5,2)
);

alter table produtos change nome_produto produto varchar(255);