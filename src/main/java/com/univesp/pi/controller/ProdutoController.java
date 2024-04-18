package com.univesp.pi.controller;

import com.univesp.pi.dto.AtualizarProduto;
import com.univesp.pi.model.ProdutoEntity;
import com.univesp.pi.service.ProdutoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @GetMapping
    private List<ProdutoEntity> getAllProdutos(){return service.getAll();}

    @GetMapping("/nome")
    private Optional<ProdutoEntity> getProodutoByName(@PathParam("name") String name) {return service.getByProdutoName(name);}

    @GetMapping("/desconto")
    private List<ProdutoEntity> getProdutosWithDesconto(){ return service.getAllWithDescontoTrue();}

    @GetMapping("/{id}")
    private Optional<ProdutoEntity> getById(@PathVariable ("id") Long id){ return service.getById(id);}

    @PostMapping
    private void createProduto(@RequestBody ProdutoEntity entity) { service.includeProduto(entity);}

    @PostMapping("/{id}")
    private void updateProduto(@PathVariable("id") Long id, @RequestBody AtualizarProduto entity) {service.atualizarProduto(id,entity);}

    @DeleteMapping("/{id}")
    private void removerProduto(@PathVariable("id") Long id) { service.deleteProduto(id);}
}
