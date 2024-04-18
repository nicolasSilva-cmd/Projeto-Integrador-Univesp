package com.univesp.pi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_produto;

    @NotNull(message = "id do mercado não pode ser nulo")
    private Long id_mercado;

    @NotNull(message = "nome não pode ser nulo")
    private String nome;

    private String imagem;

    @NotNull(message = "preco não pode ser nulo")
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
