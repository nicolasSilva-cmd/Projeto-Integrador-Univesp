package com.univesp.pi.controller;

import com.univesp.pi.dto.AtualizarMercado;
import com.univesp.pi.model.MercadoEntity;
import com.univesp.pi.service.MercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/marketplace")
@CrossOrigin("*")
public class MercadoController {

    @Autowired
    MercadoService service;

    @GetMapping
    public ResponseEntity<List<MercadoEntity>> getAllMercado(){return service.listAll();}

    @GetMapping("/nome")
    public ResponseEntity<List<MercadoEntity>> getMercadoByName(@PathParam("name") String name) { return service.findByMercadoName(name);}

    @GetMapping("/endereco")
    public ResponseEntity<List<MercadoEntity>> getMercadoByEndereco(@PathParam("endereco") String endereco) {return service.findMercadoWhereEnderecoLike(endereco);}

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MercadoEntity>> getMercadoById(@PathVariable("id") Long id) { return service.findById(id);}

    @PostMapping
    public ResponseEntity<String> incluirMercado(@RequestBody MercadoEntity entity) { return service.incluirMercado(entity);}

    @PutMapping ("/{id}")
    public ResponseEntity<String> atualizarMercado(@PathVariable("id") Long id, @RequestBody AtualizarMercado entity){ return service.atualizarMercado(id, entity);}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removermercado(@PathVariable("id") Long id){ return service.removermercado(id);}
}
