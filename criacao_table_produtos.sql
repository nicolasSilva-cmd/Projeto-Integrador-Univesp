CREATE TABLE IF NOT EXISTS produto(
    id_produto int auto_increment primary key,
    id_mercado int,
    nome varchar(255),
    preco numeric(38,2),
    imagem varchar(255),
    desconto boolean
);