package com.univesp.pi.service;

import com.univesp.pi.dto.Produto;
import com.univesp.pi.dto.ProdutoDTO;
import com.univesp.pi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private ProdutoRepository repository;

    public Page<Produto> listar(Pageable page){
        return repository.findAll(page);
    }

    public Optional<Produto> listarPorId(Integer id){
        return repository.findById(id);
    }

    public Produto incluirProduto(Produto produto){
        produto.setPreco_Total();
        return repository.save(produto);
    }

    public Produto atualizarProduto(Integer id, ProdutoDTO dto){
        if(repository.existsById(id)) {
            Produto produto = repository.getReferenceById(id);
            produto.setProduto(dto.getProduto());
            produto.setQuantidade(dto.getQuantidade());
            produto.setValor_unitario(dto.getValor_unitario());
            produto.setPreco_Total();
            repository.save(produto);
            return produto;
        }
        return new Produto();
    }

    public void deletarProduto(Integer id) {
        repository.deleteById(id);
    }

}