package com.univesp.pi.controller;

import com.univesp.pi.dto.Produto;
import com.univesp.pi.dto.ProdutoDTO;
import com.univesp.pi.service.EstoqueService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    EstoqueService service;

    @GetMapping
    public Page<Produto> listarTodos(@PageableDefault(size=10, sort = {"produto"}) Pageable page){
        return service.listar(page);
    }

    @GetMapping("/{id}")
    public Optional<Produto> listarPorId(@PathVariable("id") Integer id){
        return service.listarPorId(id);
    }

    @PostMapping
    @Transactional
    public Produto incluirProduto(@RequestBody Produto produto){
        return service.incluirProduto(produto);
    }

    @PostMapping("/{id}")
    @Transactional
    public Produto atualizarProduto(@PathVariable("id") int id,
                                   @RequestBody ProdutoDTO produto){
        return service.atualizarProduto(id, produto);
    }



    @DeleteMapping("/{id}")
    @Transactional
    public void deletarProduto(@PathVariable("id") int id){
        service.deletarProduto(id);
    }

}
