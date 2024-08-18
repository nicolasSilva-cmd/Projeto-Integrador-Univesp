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


    public void setNome(String nome) {
        if(nome != null) this.nome = nome;
    }



    public void setDesconto(Boolean desconto) {
        if(desconto != null) this.desconto = desconto;
    }
}
