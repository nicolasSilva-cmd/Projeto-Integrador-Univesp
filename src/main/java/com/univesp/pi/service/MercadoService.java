package com.univesp.pi.service;

import com.univesp.pi.dto.AtualizarMercado;
import com.univesp.pi.model.MercadoEntity;
import com.univesp.pi.repository.MercadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MercadoService {

    @Autowired
    MercadoRepository repository;

    public ResponseEntity<List<MercadoEntity>> listAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<Optional<MercadoEntity>> findById(Long id) {
        Optional<MercadoEntity> mercado = repository.findById(id);

        if (!(mercado.isPresent())) {
            throw new EntityNotFoundException("Não existe um mercado com ID " + id);
        }
        return ResponseEntity.ok(mercado);
    }

    public ResponseEntity<List<MercadoEntity>> findByMercadoName(String name) {
        List<MercadoEntity> mercado = repository.findByNomeContainingIgnoreCase(name);

        if (mercado.isEmpty()) {
            throw new EntityNotFoundException("Não existe um mercado com nome");
        }
        return ResponseEntity.ok(mercado);
    }

    public ResponseEntity<List<MercadoEntity>> findByEndereco(String endereco) {
        List<MercadoEntity> mercado = repository.findByEndereco(endereco);
        if (mercado.size() == 0) {
            throw new EntityNotFoundException("Não existe mercados nesse endereço");
        }
        return ResponseEntity.ok(mercado);
    }

    public ResponseEntity<String> incluirMercado(MercadoEntity entity) {
        repository.save(entity);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<MercadoEntity>> findMercadoWhereEnderecoLike(String endereco) {
        List<MercadoEntity> mercado = repository.findByEnderecoContaining(endereco);
        if (mercado.size() == 0) {
            throw new EntityNotFoundException("Não existe mercados nesse endereço");
        }
        return ResponseEntity.ok(mercado);
    }


    public ResponseEntity<String> removermercado(Long id) {
        if (repository.existsById(id)) repository.deleteById(id);

        else {
            throw new EntityNotFoundException("Mercado com ID " + id + " não foi encontrada");
        }

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> atualizarMercado(Long id, AtualizarMercado entity) {
        if (repository.existsById(id)) {
            MercadoEntity mercado = repository.getReferenceById(id);
            mercado.setNome(entity.getNome());
            mercado.setEndereco(entity.getEndereco());
            repository.save(mercado);
        } else{
            throw new EntityNotFoundException("Mercado com ID " + id + " não foi encontrada");
        }

        return ResponseEntity.ok().build();

    }

}
