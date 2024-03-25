package com.univesp.pi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EstoqueController {

    @GetMapping
    @RequestMapping("/estoque")
    public String listar(){

        return null;
    }

    @GetMapping
    @RequestMapping("/estoque/{id}")
    public String listarPorId(@RequestParam("id") int id){

        return null;
    }

    @PostMapping
    @RequestMapping("/estoque/{id}")
    public String atualizarProduto(@RequestParam("id") int id){

        return null;
    }

    @DeleteMapping
    @RequestMapping("/estoque/{id}")
    public void deletarProduto(@RequestParam("id") int id){


    }

}
