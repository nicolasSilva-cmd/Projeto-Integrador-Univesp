package com.univesp.pi.dto;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ProdutoDTO {

    private int id;
    private String nome;
    private int quantidade;
    private float valor_unitario;
    private float preco_total;
}
