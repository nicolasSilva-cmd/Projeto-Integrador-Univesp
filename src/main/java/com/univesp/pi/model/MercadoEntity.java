package com.univesp.pi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@Table(name = "mercado")
@EqualsAndHashCode
public class MercadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "nome do mercado não pode ser nulo")
    @Column(name = "nome_mercado")
    private String nome;

    @NotNull(message = "endereco não pode ser nulo")
    @Column(name = "endereco")
    private String endereco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mercado")
    private List<ProdutoEntity> produtos;

    public void setNome(String nome) {
        if(nome != null) this.nome = nome;
    }

    public void setEndereco(String endereco) {
        if(endereco != null) this.endereco = endereco;
    }
}
