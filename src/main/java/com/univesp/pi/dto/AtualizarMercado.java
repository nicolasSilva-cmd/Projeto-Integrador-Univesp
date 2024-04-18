package com.univesp.pi.dto;

import lombok.Getter;

@Getter
public class AtualizarMercado {

    private long id;

    private String nome;

    private String endereco;

    public void setNome(String nome) {
        if(nome != null) this.nome = nome;
    }

    public void setEndereco(String endereco) {
        if(endereco != null) this.endereco = endereco;
    }
}
