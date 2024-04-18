package com.univesp.pi.service;

import com.univesp.pi.dto.AtualizarProduto;
import com.univesp.pi.model.ProdutoEntity;
import com.univesp.pi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public List<ProdutoEntity> getAll(){return repository.findAll();}

    public Optional<ProdutoEntity> getById(Long id) {return repository.findById(id);}

    public List<ProdutoEntity> getAllWithDescontoTrue(){return repository.findAllByDescontoIsTrue();}

    public Optional<ProdutoEntity> getByProdutoName(String nome) { return repository.findAllByNome(nome);}

    public void includeProduto(ProdutoEntity entity) {repository.save(entity);}

    public void atualizarProduto(Long id, AtualizarProduto entity){
        if (repository.existsById(id)){
            ProdutoEntity produto = repository.getReferenceById(id);
            produto.setNome(entity.getNome());
            produto.setImagem(entity.getImagem());
            produto.setPreco(entity.getPreco());
            produto.setId_mercado(entity.getId_mercado());
            produto.setDesconto(entity.getDesconto());
            repository.save(produto);
        }
    }

    public void deleteProduto(Long id){
        if(repository.existsById(id)){repository.deleteById(id);}
    }
}
