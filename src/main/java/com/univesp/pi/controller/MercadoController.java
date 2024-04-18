package com.univesp.pi.controller;

import com.univesp.pi.dto.AtualizarMercado;
import com.univesp.pi.model.MercadoEntity;
import com.univesp.pi.service.MercadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marketplace")
public class MercadoController {

    @Autowired
    MercadoService service;

    @GetMapping
    public List<MercadoEntity> getAllMercado(){return service.listAll();}

    @GetMapping("/nome")
    public Optional<MercadoEntity> getMercadoByName(@PathParam("name") String name) { return service.findByMercadoName(name);}

    @GetMapping("/endereco")
    public List<MercadoEntity> getMercadoByEndereco(@PathParam("endereco") String endereco) {return service.findByEndereco(endereco);}

    @GetMapping("/{id}")
    public Optional<MercadoEntity> getMercadoById(@PathVariable("id") Long id) { return service.findById(id);}

    @PostMapping
    public void incluirMercado(@RequestBody MercadoEntity entity) {service.incluirMercado(entity);}

    @PostMapping("/{id}")
    public void atualizarMercado(@PathVariable("id") Long id, @RequestBody AtualizarMercado entity){ service.atualizarMercado(id, entity);}

    @DeleteMapping("/{id}")
    public void removermercado(@PathVariable("id") Long id){ service.removermercado(id);}
}
