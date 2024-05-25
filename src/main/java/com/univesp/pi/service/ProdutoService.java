package com.univesp.pi.service;

import com.univesp.pi.dto.AtualizarProduto;
import com.univesp.pi.model.ProdutoEntity;
import com.univesp.pi.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repository;

    public ResponseEntity<List<ProdutoEntity>> getAll(){return ResponseEntity.ok(repository.findAll());}

    public ResponseEntity<Optional<ProdutoEntity>> getById(Long id) {
        Optional<ProdutoEntity> produto = repository.findById(id);
        if(!(produto.isPresent())) {
            throw new EntityNotFoundException("Não existe um produto com o ID " + id);
        }
        return ResponseEntity.ok(produto);

    }

    public ResponseEntity<List<ProdutoEntity>> getAllWithDescontoTrue(){
        List<ProdutoEntity> produto = repository.findAllByDescontoIsTrue();
        if(produto.size() == 0){
            throw new EntityNotFoundException("Não existem produtos com desconto");
        }
        return ResponseEntity.ok(produto);}

    public ResponseEntity<List<ProdutoEntity>> getByProdutoName(String nome) {
        List<ProdutoEntity> produto = repository.findByNomeContainingIgnoreCase(nome);
        if(produto.isEmpty()){
            throw new EntityNotFoundException("Não existe um produto com o nome: " + nome);

        }
        return ResponseEntity.ok(produto);
    }

    public ResponseEntity<String> includeProduto(ProdutoEntity entity) {
        repository.save(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> atualizarProduto(Long id, AtualizarProduto entity){
        if (repository.existsById(id)){
            ProdutoEntity produto = repository.getReferenceById(id);
            produto.setNome(entity.getNome());
            produto.setImagem(entity.getImagem());
            produto.setPreco(entity.getPreco());
            produto.setId_mercado(entity.getId_mercado());
            produto.setDesconto(entity.getDesconto());
            repository.save(produto);
        } else {
            throw new EntityNotFoundException("Produto com ID " + id + " não foi encontrado");
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> deleteProduto(Long id){

        if(repository.existsById(id))
        {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Produto com ID " + id + " não foi encontrado");
        }
        return ResponseEntity.ok().build();

    }
}
