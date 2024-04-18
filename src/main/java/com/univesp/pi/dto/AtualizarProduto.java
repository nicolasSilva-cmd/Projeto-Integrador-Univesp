package com.univesp.pi.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AtualizarProduto {

    private long id_produto;

    private Long id_mercado;

    private String nome;

    private String imagem;

    private BigDecimal preco;

    private Boolean desconto;

    public void setId_mercado(Long id_mercado) {
        if(id_mercado != null) this.id_mercado = id_mercado;
    }

    public void setNome(String nome) {
        if(nome != null) this.nome = nome;
    }

    public void setImagem(String imagem) {
        if(imagem != null) this.imagem = imagem;
    }

    public void setPreco(BigDecimal preco) {
        if(preco != null) this.preco = preco;
    }

    public void setDesconto(Boolean desconto) {
        if(desconto != null) this.desconto = desconto;
    }
}
