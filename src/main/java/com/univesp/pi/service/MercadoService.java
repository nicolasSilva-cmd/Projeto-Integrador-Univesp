package com.univesp.pi.service;

import com.univesp.pi.dto.AtualizarMercado;
import com.univesp.pi.model.MercadoEntity;
import com.univesp.pi.repository.MercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MercadoService {

    @Autowired
    MercadoRepository repository;

    public List<MercadoEntity> listAll(){return repository.findAll();}

    public Optional<MercadoEntity> findById(Long id){return repository.findById(id);}

    public Optional<MercadoEntity> findByMercadoName(String name) { return repository.findByNome(name);}

    public List<MercadoEntity> findByEndereco(String endereco){return repository.findByEndereco(endereco);}

    public void incluirMercado(MercadoEntity entity) { repository.save(entity);}

    public void removermercado(Long id) { if(repository.existsById(id)) repository.deleteById(id);}

    public void atualizarMercado(Long id, AtualizarMercado entity){
        if(repository.existsById(id)){
            MercadoEntity mercado = repository.getReferenceById(id);
            mercado.setNome(entity.getNome());
            mercado.setEndereco(entity.getEndereco());
            repository.save(mercado);
        }


    }

}
