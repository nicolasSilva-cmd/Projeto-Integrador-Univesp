package com.univesp.pi.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// Representa a tabela no Banco de Dados.

@Entity
@Data
@Getter
@Table(name = "Produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String produto;
    private int quantidade;

    private float preco_unitario;

    private float preco_total;

    public void setProduto(String produto) {
        if(produto != null) this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        if(!(quantidade <= 0)) this.quantidade = quantidade;
    }

    public void setValor_unitario(float valor_unitario) {
        if(!(valor_unitario <= 0.0)) this.preco_unitario = valor_unitario;
    }

    public void setPreco_total_Dto(float multiplicacao) {
        preco_total = multiplicacao;
    }

    public void setPreco_Total(){
        preco_total = quantidade * preco_unitario;
    }
}
