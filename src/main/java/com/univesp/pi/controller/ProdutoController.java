package com.univesp.pi.controller;

import com.univesp.pi.dto.AtualizarProduto;
import com.univesp.pi.model.ProdutoEntity;
import com.univesp.pi.service.ProdutoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService service;

    @GetMapping
    private ResponseEntity<List<ProdutoEntity>> getAllProdutos(){return service.getAll();}

    @GetMapping("/nome")
    private ResponseEntity<List<ProdutoEntity>>  getProodutoByName(@PathParam("name") String name) {return service.getByProdutoName(name);}

    @GetMapping("/desconto")
    private ResponseEntity<List<ProdutoEntity>> getProdutosWithDesconto(){ return service.getAllWithDescontoTrue();}

    @GetMapping("/{id}")
    private ResponseEntity<Optional<ProdutoEntity>> getById(@PathVariable ("id") Long id){ return service.getById(id);}

    @PostMapping
    private ResponseEntity<String> createProduto(@RequestBody ProdutoEntity entity) { return service.includeProduto(entity);}

    @PutMapping("/{id}")
    private ResponseEntity<String> updateProduto(@PathVariable("id") Long id, @RequestBody AtualizarProduto entity) { return service.atualizarProduto(id,entity);}

    @DeleteMapping("/{id}")
    private ResponseEntity<String> removerProduto(@PathVariable("id") Long id) { return service.deleteProduto(id);}
}
